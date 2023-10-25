package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.Image
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun LikeScreen(
    viewModel: ViewModel,
    zoomImage: (Image?) -> Unit = {},
    onLongClick: (DataSource) -> Unit = {},
    onClearClick: () -> Unit = {}
) {
    viewModel.getDataSource()
    val data by viewModel.dataSourceLocal.observeAsState(mutableListOf())

    Scaffold(
        floatingActionButton = {
            ClearFloatingActionButton(
                onClearClick = onClearClick
            )
        }
    ) {
        LazyColumnFun(
            modifier = Modifier.padding(it),
            zoomImage = zoomImage,
            onLongClick = onLongClick,
            data = data
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
private fun LazyColumnFun(
    modifier: Modifier,
    zoomImage: (Image?) -> Unit = {},
    onLongClick: (DataSource) -> Unit = {},
    data: MutableList<DataSource>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
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
private fun LikeImage(
    dataSource: DataSource,
    zoomImage: (Image?) -> Unit = {},
    onLongClick: (DataSource) -> Unit = {}
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(dataSource.image?.url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.baseline_image_24),
        error = painterResource(R.drawable.baseline_image_24),
        contentDescription = "",
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
                        zoomImage(dataSource.image)
                    }
                )
            }
    )
}

@Composable
private fun ClearFloatingActionButton(onClearClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClearClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = ""
            )
        }
    }
}