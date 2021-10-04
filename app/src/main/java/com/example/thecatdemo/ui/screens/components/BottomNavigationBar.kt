package com.example.thecatdemo.ui.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.thecatdemo.ui.NavItem
import com.example.thecatdemo.ui.theme.TheCatDemoTheme

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem.Main,
        NavItem.Like
    )

    BottomNavigation(
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    item.icon?.let {
                        Icon(
                            painterResource(id = it),
                            contentDescription = ""
                        )
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White,
                selected = false,
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

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    TheCatDemoTheme() {
        BottomNavigationBar(navController = rememberNavController())
    }
}

@ExperimentalCoilApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreviewDark() {
    TheCatDemoTheme(darkTheme = true) {
        BottomNavigationBar(navController = rememberNavController())
    }
}