package com.example.thecatdemo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.thecatdemo.R
import com.example.thecatdemo.data.source.DataSource
import com.example.thecatdemo.data.source.fakeDataSource
import com.example.thecatdemo.ui.theme.TheCatDemoTheme
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@Composable
fun DetailsScreen(viewModel: ViewModel) {
    val item: DataSource? = viewModel.clickDataSource
    if (item != null) {
        DataDetail(item)
    }
}

@ExperimentalCoilApi
@Composable
private fun DataDetail(dataSource: DataSource) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DataImageDetails(dataSource)
        ProfileContent(dataSource)
    }
}

@ExperimentalCoilApi
@Composable
private fun DataImageDetails(dataSource: DataSource) {
    Image(
        rememberImagePainter(data = dataSource.image.url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(400.dp)
    )
}

@Composable
private fun ProfileContent(dataSource: DataSource) {
    Column {
        Name(dataSource)
        ProfileProperty(stringResource(R.string.origin), dataSource.origin)
        ProfileProperty(stringResource(R.string.description), dataSource.description)
        ProfileProperty(stringResource(R.string.temperament), dataSource.temperament)
        ProfileProperty(stringResource(R.string.wikipedia_url), dataSource.wikipedia_url)
    }
}

@Composable
private fun Name(dataSource: DataSource) {
    Column(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {
        Text(
            text = dataSource.name,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfileProperty(label: String, value: String) {
    Column(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.caption,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Visible
        )
    }
}

@ExperimentalCoilApi
@Composable
@Preview
private fun DataDetailPreview() {
    TheCatDemoTheme() {
        Scaffold {
            DataDetail(dataSource = fakeDataSource)
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
private fun DataDetailPreviewDark() {
    TheCatDemoTheme(darkTheme = true) {
        Scaffold {
            DataDetail(dataSource = fakeDataSource)
        }
    }
}