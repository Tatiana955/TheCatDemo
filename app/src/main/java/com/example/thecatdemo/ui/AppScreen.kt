package com.example.thecatdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.example.thecatdemo.R
import com.example.thecatdemo.ui.screens.*
import com.example.thecatdemo.ui.screens.WebScreen
import com.example.thecatdemo.ui.screens.components.BottomNavigationBar
import com.example.thecatdemo.ui.screens.components.MyTopAppBar
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalFoundationApi
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
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun AppNavHost(navController: NavHostController,
            modifier: Modifier = Modifier,
            viewModel: ViewModel
) {
    NavHost(
        navController,
        startDestination = NavItem.Main.route,
        modifier = modifier
    ) {

        composable(NavItem.Main.route) {
            MainScreen(viewModel, onClickDetails = {
                navController.navigate(NavItem.Details.route)
                viewModel.clickDataSource = it
            })
        }

        composable(NavItem.Details.route) {
            DetailsScreen(viewModel, onClickItem = {
                navController.navigate(NavItem.Web.route)
            })
        }

        composable(NavItem.Web.route) { WebScreen(viewModel) }

        composable(NavItem.Like.route) {
            LikeScreen(viewModel, zoomImage = {
                navController.navigate(NavItem.Zoom.route)
                viewModel.zoomImage = it
            })
        }

        composable(NavItem.Zoom.route) { ZoomScreen(viewModel) }
    }
}

sealed class NavItem(var route: String, var icon: Int?, var title: String) {
    object Main : NavItem("main", R.drawable.ic_baseline_pets_24, "MainScreen")
    object Like : NavItem("like", R.drawable.ic_baseline_like_grey_24, "DetailsScreen")
    object Details : NavItem("details", null, "WebScreen")
    object Web : NavItem("web", null, "LikeScreen")
    object Zoom : NavItem("zoom", null, "ZoomScreen")
}