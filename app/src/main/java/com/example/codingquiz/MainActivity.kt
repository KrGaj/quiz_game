package com.example.codingquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codingquiz.navigation.AppNavHost
import com.example.codingquiz.navigation.BottomNavBar
import com.example.codingquiz.navigation.Screen
import com.example.codingquiz.ui.theme.CodingQuizTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // FIXME
            KoinContext {
                CodingQuizTheme {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    // A surface container using the 'background' color from the theme
                    Scaffold(
                        bottomBar = {
                            when (navBackStackEntry?.destination?.route) {
                                Screen.Categories.routeBase,
                                Screen.Statistics.routeBase -> BottomNavBar(
                                    navController = navController,
                                    destinationRoute = navBackStackEntry?.destination?.route,
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
}
