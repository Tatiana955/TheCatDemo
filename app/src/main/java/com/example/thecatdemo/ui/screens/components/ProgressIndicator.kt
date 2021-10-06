package com.example.thecatdemo.ui.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thecatdemo.ui.theme.TheCatDemoTheme

@Composable
fun AppCircularProgressIndicator() {
    SimpleCircularProgressIndicator()
}

@Composable
private fun SimpleCircularProgressIndicator() {
    Column(
        modifier = Modifier
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
            AppCircularProgressIndicator()
        }
    }
}

@Composable
@Preview
private fun FeaturedCircularProgressIndicatorPreviewDark(){
    TheCatDemoTheme(darkTheme = true) {
        Surface() {
            AppCircularProgressIndicator()
        }
    }
}