package com.example.codingquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codingquiz.di.databaseModule
import com.example.codingquiz.di.repositoryModule
import com.example.codingquiz.di.viewModelModule
import com.example.codingquiz.navigation.AppNavHost
import com.example.codingquiz.navigation.Screen
import com.example.codingquiz.ui.theme.CodingQuizTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinApplication(application = {
                androidContext(application)
                androidLogger()

                modules(
                    listOf(
                        databaseModule,
                        repositoryModule,
                        viewModelModule,
                    )
                )
            }) {
                CodingQuizTheme {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    // A surface container using the 'background' color from the theme
                    Scaffold(
                        bottomBar = {
                            when (navBackStackEntry?.destination?.route) {
                                Screen.Categories.route,
                                Screen.Statistics.route -> BottomNavBar(
                                    navController = navController,
                                    navBackStackEntry = navBackStackEntry,
                                )
                                else -> Unit
                            }
                        },
                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AppNavHost(navController)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomNavBar(
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
    ) {
        BottomNavigation {
            BottomNavigationItem(
                selected = navBackStackEntry?.destination?.route == Screen.Categories.route,
                onClick = {
                    navController.navigate(Screen.Categories.route) {
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

            BottomNavigationItem(
                selected = navBackStackEntry?.destination?.route == Screen.Statistics.route,
                onClick = { navController.navigate(Screen.Statistics.route) },
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
}
