package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.fakeDataSource
import com.example.thecatdemo.ui.theme.TheCatDemoTheme
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@Composable
fun MainScreen(viewModel: ViewModel,
               onClickDetails: (DataSource) -> Unit = {}
) {
    val data by viewModel.dataSource.observeAsState(mutableListOf())

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        this.items(items = data, itemContent = { item ->
            ListItem(viewModel = viewModel, dataSource = item, onClickDetails)
        })
    }
}

@ExperimentalCoilApi
@Composable
private fun ListItem(viewModel: ViewModel,
                     dataSource: DataSource,
                     onClickDetails: (DataSource) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClickDetails(dataSource)
            },
        elevation = 2.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DataImage(dataSource)

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                dataSource.name?.let {
                    Text(text = it, style = MaterialTheme.typography.h6)
                }
                dataSource.origin?.let {
                    Text(text = it, style = MaterialTheme.typography.caption)
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.End)
                ) {
                    Like(viewModel, dataSource)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun DataImage(dataSource: DataSource) {
    Image(
        rememberImagePainter(data = dataSource.image?.url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Composable
fun Like(viewModel: ViewModel, dataSource: DataSource) {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_like_grey_24),
        contentDescription = null,
        modifier = Modifier.clickable {
            viewModel.insertDataSource(dataSource)
        }
    )
}

@ExperimentalCoilApi
@Composable
@Preview
private fun ListItemPreview() {
    TheCatDemoTheme(darkTheme = false) {
        Scaffold {
            ListItem(viewModel(), dataSource = fakeDataSource, onClickDetails = {})
        }
    }
}

@ExperimentalCoilApi
@Preview
@Composable
private fun ListItemPreviewDark() {
    TheCatDemoTheme(darkTheme = true) {
        Scaffold {
            ListItem(viewModel(), dataSource = fakeDataSource, onClickDetails = {})
        }
    }
}
