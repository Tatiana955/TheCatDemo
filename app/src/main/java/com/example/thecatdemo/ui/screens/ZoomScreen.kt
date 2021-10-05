package com.example.thecatdemo.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thecatdemo.viewmodel.ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.data.source.Image
import com.example.thecatdemo.data.source.fakeDataSource

@ExperimentalCoilApi
@Composable
fun ZoomScreen(viewModel: ViewModel) {
    viewModel.zoomImage?.let { ZoomImage(it) }
}

@ExperimentalCoilApi
@Composable
fun ZoomImage(image: Image) {
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
        Image(rememberImagePainter(data = image.url),
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offsetX,
                    translationY = offsetY
                ),
            contentDescription = ""
        )
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun ZoomableComposablePreview() {
    fakeDataSource.image?.let { ZoomImage(image = it) }
}