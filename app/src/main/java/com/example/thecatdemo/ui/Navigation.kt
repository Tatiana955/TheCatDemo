package com.example.thecatdemo.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.R
import com.example.thecatdemo.ui.screens.*
import com.example.thecatdemo.viewmodel.ViewModel

@ExperimentalComposeUiApi
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
            LikeScreen(
                viewModel,
                zoomImage = {
                    navController.navigate(NavItem.Zoom.route)
                    viewModel.zoomImage = it
                },
                onLongClick = {
                    navController.navigate(NavItem.DialogDelete.route)
                    viewModel.clickDataSource = it
                }
            )
        }

        composable(NavItem.Zoom.route) { ZoomScreen(viewModel) }

        composable(NavItem.DialogDelete.route) {
            DialogDeleteScreen(
                viewModel,
                onDismiss = {
                    navController.popBackStack()
                },
                onNegativeClick = {
                    navController.popBackStack()
                },
                onPositiveClick = {
                    viewModel.clickDataSource?.primaryKey?.let {
                        viewModel.deleteByPrimaryKey(it)
                    }
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class NavItem(var route: String, var icon: Int?, var title: String) {
    object Main : NavItem("main", R.drawable.ic_baseline_pets_24, "MainScreen")
    object Like : NavItem("like", R.drawable.ic_baseline_like_grey_24, "DetailsScreen")
    object Details : NavItem("details", null, "WebScreen")
    object Web : NavItem("web", null, "LikeScreen")
    object Zoom : NavItem("zoom", null, "ZoomScreen")
    object DialogDelete : NavItem("dialog_delete", null, "DialogDeleteScreen")
}