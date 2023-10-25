package com.example.thecatdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.ui.screens.components.BottomNavigationBar
import com.example.thecatdemo.ui.screens.components.MyTopAppBar
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun AppScreen(viewModel: ViewModel) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = {
                    Text(text = "The cats")
                }
            )
        },
        content = { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    )
}