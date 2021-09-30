package com.example.thecatdemo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.ui.screens.DetailsScreen
import com.example.thecatdemo.ui.screens.MainScreen
import com.example.thecatdemo.ui.screens.components.MyTopAppBar
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalCoilApi
@Composable
fun AppScreen(viewModel: ViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
                TopAppBar() {
                    MyTopAppBar(
                        title = {
                            Text(text = "MainScreen") }
                    )
                }
        },
        content = { innerPadding ->

            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
  
        }
    )
}

@ExperimentalCoilApi
@Composable
fun AppNavHost(navController: NavHostController,
            modifier: Modifier = Modifier,
            viewModel: ViewModel
) {

    NavHost(navController, startDestination = "main", modifier = modifier) {

        composable("main") { MainScreen(viewModel, onClickDetails = {
            navController.navigate("details")
            viewModel.clickDataSource = it
        })
        }
        composable("details") { DetailsScreen(viewModel) }
    }
}