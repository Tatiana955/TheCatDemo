package com.example.thecatdemo.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thecatdemo.ui.theme.TheCatDemoTheme

@Composable
fun AppCircularProgressIndicator(modifier: Modifier = Modifier) {
    SimpleCircularProgressIndicator(modifier = modifier)
}

@Composable
private fun SimpleCircularProgressIndicator(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
private fun FeaturedCircularProgressIndicatorPreview(){
    TheCatDemoTheme() {
        Surface() {
            AppCircularProgressIndicator(modifier = Modifier)
        }
    }
}

@Composable
@Preview
private fun FeaturedCircularProgressIndicatorPreviewDark(){
    TheCatDemoTheme(darkTheme = true) {
        Surface() {
            AppCircularProgressIndicator(modifier = Modifier)
        }
    }
}