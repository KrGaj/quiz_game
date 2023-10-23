package com.example.codingquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.codingquiz.ui.screen.CategoryGrid
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.theme.CodingQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = NavigationConstants.CATEGORIES) {
                        composable(NavigationConstants.CATEGORIES) {
                            CategoryGrid {
                                navController.navigate("${NavigationConstants.QUESTION}/${it.id}")
                            }
                        }

                        composable(
                            "${NavigationConstants.QUESTION}/{${NavigationConstants.CATEGORY_ID}}",
                            arguments = listOf(navArgument(NavigationConstants.CATEGORY_ID) {
                                type = NavType.IntType
                            }),
                        ) { backStackEntry ->
                            QuestionScreen(
                                categoryId = backStackEntry.arguments?.getInt(NavigationConstants.CATEGORY_ID)
                            )
                        }
                    }
                }
            }
        }
    }
}
