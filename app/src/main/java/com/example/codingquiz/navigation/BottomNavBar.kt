package com.example.codingquiz.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Composable
fun BottomNavBar(
    navController: NavHostController,
    destinationRoute: String?,
) {
    NavigationBar {
        NavigationBarItem(
            selected = destinationRoute == Screen.Categories.routeBase,
            onClick = {
                navController.navigate(Screen.Categories.routeBase) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            },
            icon = {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = Screen.Categories.resourceId))
            },
        )

        NavigationBarItem(
            selected = destinationRoute == Screen.Statistics.routeBase,
            onClick = { navController.navigate(Screen.Statistics.routeBase) },
            icon = {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = null
                )
            },
            label = {
                Text(text = stringResource(id = Screen.Statistics.resourceId))
            },
        )
    }
}
