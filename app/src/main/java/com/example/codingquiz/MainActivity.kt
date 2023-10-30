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
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.screen.CategoriesScreen
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.screen.QuizResultsScreen
import com.example.codingquiz.ui.theme.CodingQuizTheme
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = Json {
            isLenient = true
        }

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
                                val resultsJson = json.encodeToString(ListSerializer(QuizResult.serializer()), it)
                                navController.navigate("${NavigationConstants.QUIZ_RESULTS_SCREEN}/$resultsJson")
                            }
                        }

                        composable(
                            route = "${NavigationConstants.QUIZ_RESULTS_SCREEN}/{${NavigationConstants.QUIZ_RESULTS_ARG}}",
                            arguments = listOf(navArgument(NavigationConstants.QUIZ_RESULTS_ARG) {
                                type = NavType.StringType
                            }),
                        ) { backStackEntry ->
                            val results = backStackEntry.arguments
                                ?.getString(NavigationConstants.QUIZ_RESULTS_ARG)?.let {
                                    json.decodeFromString<List<QuizResult>>(it)
                                } ?: emptyList()

                            QuizResultsScreen(quizResults = results)
                        }
                    }
                }
            }
        }
    }
}
