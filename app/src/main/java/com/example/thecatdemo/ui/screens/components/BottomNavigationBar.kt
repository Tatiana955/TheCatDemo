package com.example.thecatdemo.ui.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.ui.NavItem

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem.Main,
        NavItem.Like
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        contentColor = Color.White
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            painterResource(id = it),
                            contentDescription = ""
                        )
                    }
                },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White,
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}