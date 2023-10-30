package com.example.codingquiz

import android.os.Build
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
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.screen.CategoriesScreen
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.screen.QuizResultsScreen
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
                    NavHost(
                        navController = navController,
                        startDestination = NavigationConstants.CATEGORIES_SCREEN,
                    ) {
                        composable(
                            route = NavigationConstants.CATEGORIES_SCREEN,
                        ) {
                            CategoriesScreen {
                                navController.navigate("${NavigationConstants.QUESTION_SCREEN}/${it.id}")
                            }
                        }

                        composable(
                            route = "${NavigationConstants.QUESTION_SCREEN}/{${NavigationConstants.CATEGORY_ID_ARG}}",
                            arguments = listOf(navArgument(NavigationConstants.CATEGORY_ID_ARG) {
                                type = NavType.IntType
                            }),
                        ) { backStackEntry ->
                            QuestionScreen(
                                categoryId = backStackEntry.arguments
                                    ?.getInt(NavigationConstants.CATEGORY_ID_ARG)
                            ) {
                                navController.navigate("${NavigationConstants.QUIZ_RESULTS_SCREEN}/$it")
                            }
                        }

                        composable(
                            route = "${NavigationConstants.QUIZ_RESULTS_SCREEN}/{${NavigationConstants.QUIZ_RESULTS_ARG}}",
                            arguments = listOf(navArgument(NavigationConstants.QUIZ_RESULTS_ARG) {
                                type = NavType.SerializableArrayType(QuizResult::class.java)
                            }),
                        ) { backStackEntry ->
                            val results = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                backStackEntry.arguments
                                    ?.getSerializable(NavigationConstants.QUIZ_RESULTS_ARG, Array<QuizResult>::class.java) ?: emptyArray()
                            } else {
                                backStackEntry.arguments?.getSerializable(NavigationConstants.QUIZ_RESULTS_ARG) as? Array<QuizResult> ?: emptyArray()
                            }

                            QuizResultsScreen(quizResults = results)
                        }
                    }
                }
            }
        }
    }
}
