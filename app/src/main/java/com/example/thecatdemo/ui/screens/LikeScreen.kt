package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.Image
import com.example.thecatdemo.data.source.fakeDataSource
import com.example.thecatdemo.ui.theme.TheCatDemoTheme
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun LikeScreen(viewModel: ViewModel,
               zoomImage: (Image) -> Unit = {},
               onLongClick: (DataSource) -> Unit = {}
) {
    viewModel.getDataSource()
    val data by viewModel.dataSourceLocal.observeAsState(mutableListOf())

    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        this.items(items = data, itemContent = { item ->
            LikeImage(
                dataSource = item,
                zoomImage = zoomImage,
                onLongClick = onLongClick
            )
        })
    }
}

@ExperimentalCoilApi
@Composable
private fun LikeImage(dataSource: DataSource,
                      zoomImage: (Image) -> Unit = {},
                      onLongClick: (DataSource) -> Unit = {}
) {
    Image(
        rememberImagePainter(data = dataSource.image?.url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        onLongClick(dataSource)
                    },
                    onTap = {
                        dataSource.image?.let { zoomImage(it) }
                    }
                )
            }
    )
}

@ExperimentalCoilApi
@Composable
@Preview
private fun ListImagePreview() {
    TheCatDemoTheme {
        LikeImage(dataSource = fakeDataSource)
    }
}
