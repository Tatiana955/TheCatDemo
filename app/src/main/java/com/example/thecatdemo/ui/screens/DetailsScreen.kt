package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@Composable
fun DetailsScreen(
    viewModel: ViewModel,
    onClickItem: (String) -> Unit = {}
) {
    val item: DataSource? = viewModel.clickDataSource
    if (item != null) {
        DataDetail(item, onClickItem)
    }
}

@ExperimentalCoilApi
@Composable
private fun DataDetail(
    dataSource: DataSource,
    onClickItem: (String) -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DataImageDetails(dataSource)
        ProfileContent(dataSource, onClickItem)
    }
}

@ExperimentalCoilApi
@Composable
private fun DataImageDetails(dataSource: DataSource) {
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
            .size(400.dp)
    )
}

@Composable
private fun ProfileContent(
    dataSource: DataSource,
    onClickItem: (String) -> Unit = {}
) {
    Column {
        Name(dataSource)
        dataSource.origin?.let {
            ProfileProperty(stringResource(R.string.origin), it)
        }
        dataSource.description?.let {
            ProfileProperty(stringResource(R.string.description), it)
        }
        dataSource.temperament?.let {
            ProfileProperty(stringResource(R.string.temperament), it)
        }
        dataSource.wikipedia_url?.let {
            ProfileProperty(stringResource(R.string.wikipedia_url), it, onClickItem)
        }
    }
}

@Composable
private fun Name(dataSource: DataSource) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
    ) {
        dataSource.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProfileProperty(
    label: String,
    value: String,
    onClickItem: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Divider(
            modifier = Modifier
                .padding(bottom = 4.dp)
        )
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible,
            modifier = Modifier
                .clickable {
                    onClickItem(value)
                }
        )
    }
}