package com.example.thecatdemo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thecatdemo.viewmodel.ViewModel
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.Image

@ExperimentalCoilApi
@Composable
fun ZoomScreen(viewModel: ViewModel) {
    val image = viewModel.zoomImage
    ZoomImage(image)
}

@ExperimentalCoilApi
@Composable
private fun ZoomImage(image: Image?) {
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()
                        do {
                            val event = awaitPointerEvent()
                            scale *= event.calculateZoom()
                            val offset = event.calculatePan()
                            offsetX += offset.x
                            offsetY += offset.y
                        } while (event.changes.any { it.pressed })
                    }
                }
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image?.url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.baseline_image_24),
            error = painterResource(R.drawable.baseline_image_24),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                )
        )
    }
}