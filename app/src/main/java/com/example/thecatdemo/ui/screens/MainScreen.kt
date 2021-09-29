package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.fakeDataSource
import com.example.thecatdemo.ui.screens.components.MyTopAppBar
import com.example.thecatdemo.ui.theme.TheCatDemoTheme
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@Composable
fun MainScreen(viewModel: ViewModel) {
    val data by viewModel.dataSource.observeAsState(mutableListOf())

    Column() {

        TopAppBar() {
            MyTopAppBar(
                title = {
                    Text(text = "MainScreen") }
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            this.items(items = data, itemContent = { item ->
                ListItem(dataSource = item)
            })
        }
    }

}

@ExperimentalCoilApi
@Composable
fun ListItem(dataSource: DataSource) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            DataImage(dataSource)

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dataSource.name, style = MaterialTheme.typography.h6)
                Text(text = dataSource.origin, style = MaterialTheme.typography.caption)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DataImage(dataSource: DataSource) {
    Image(
        rememberImagePainter(data = dataSource.image.url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@ExperimentalCoilApi
@Composable
@Preview
fun ListItemPreview() {
    TheCatDemoTheme(darkTheme = false) {
        Surface {
            ListItem(dataSource = fakeDataSource)
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
fun ListItemPreviewDark() {
    TheCatDemoTheme(darkTheme = true) {
        Surface {
            ListItem(dataSource = fakeDataSource)
        }
    }
}
