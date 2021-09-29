package com.example.thecatdemo.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thecatdemo.R
import com.example.thecatdemo.ui.theme.TheCatDemoTheme

@Composable
fun MyTopAppBar(
    title: @Composable RowScope.() -> Unit,
) {

    Column() {
        TopAppBar(
            elevation = 0.dp,
            contentColor = Color.White,
            title = { Row { title() } },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_pets_24),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        )
    }
}

@Preview
@Composable
fun MyTopAppBarPreview() {
    TheCatDemoTheme() {
        Surface {
            MyTopAppBar(title = { Text("Предварительный просмотр") })
        }
    }
}

@Preview
@Composable
fun MyTopAppBarPreviewDark() {
    TheCatDemoTheme(darkTheme = true) {
        Surface {
        MyTopAppBar(title = { Text("Предварительный просмотр") })
        }
    }
}