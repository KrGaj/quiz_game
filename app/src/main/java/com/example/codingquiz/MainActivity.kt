package com.example.codingquiz

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.data.domain.QuizResults
import com.example.codingquiz.ui.dialogs.ExitAppDialog
import com.example.codingquiz.ui.dialogs.ExitQuizDialog
import com.example.codingquiz.ui.screen.CategoriesScreen
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.screen.QuizResultsScreen
import com.example.codingquiz.ui.theme.CodingQuizTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
                            CategoriesScreen(
                                onBackPressed = {
                                    navController.navigate(NavigationConstants.EXIT_APP_DIALOG)
                                }
                            ) {
                                navController.navigate(
                                    route = "${NavigationConstants.QUESTION_SCREEN}/${it.id}",
                                ) {
                                    popUpTo(
                                        NavigationConstants.CATEGORIES_SCREEN,
                                    ) {
                                        inclusive = true
                                    }
                                }
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
                                    ?.getInt(NavigationConstants.CATEGORY_ID_ARG),
                                onBackPressed = {
                                    val resultsJson = Uri.encode(Json.encodeToString(QuizResults(it)))
                                    navController.navigate("${NavigationConstants.EXIT_QUIZ_DIALOG}/$resultsJson")
                                },
                                navigateToResults = { navigateToResultsScreen(it, navController) },
                            )
                        }

                        composable(
                            route = "${NavigationConstants.QUIZ_RESULTS_SCREEN}/{${NavigationConstants.QUIZ_RESULTS_ARG}}",
                            arguments = listOf(navArgument(NavigationConstants.QUIZ_RESULTS_ARG) {
                                type = QuizResultNavType()
                            }),
                        ) { backStackEntry ->
                            val results = deserializeQuizResults(backStackEntry)

                            QuizResultsScreen(
                                quizResults = results,
                                onBackPressed = { backToCategoriesInclusive(navController) },
                                navigateToCategories = { backToCategoriesInclusive(navController) },
                            )
                        }
                        
                        dialog(
                            route = "${NavigationConstants.EXIT_QUIZ_DIALOG}/{${NavigationConstants.QUIZ_RESULTS_ARG}}",
                            dialogProperties = DialogProperties(
                                dismissOnBackPress = true,
                                dismissOnClickOutside = true,
                            ),
                            arguments = listOf(navArgument(NavigationConstants.QUIZ_RESULTS_ARG) {
                                type = QuizResultNavType()
                            })
                        ) { backStackEntry ->
                            val results = deserializeQuizResults(backStackEntry)

                            ExitQuizDialog(
                                onDismissRequest = { navController.popBackStack() },
                                onConfirmation = {
                                    if (results.isEmpty()) {
                                        navController.navigate(NavigationConstants.CATEGORIES_SCREEN) {
                                            popUpTo(NavigationConstants.QUESTION_SCREEN) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        navigateToResultsScreen(results, navController)
                                    }
                                }
                            )
                        }

                        dialog(
                            route = NavigationConstants.EXIT_APP_DIALOG,
                            dialogProperties = DialogProperties(
                                dismissOnBackPress = true,
                                dismissOnClickOutside = true,
                            )
                        ) {
                            ExitAppDialog(
                                onDismissRequest = { navController.popBackStack() },
                                onConfirmation = {
                                    finish()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateToResultsScreen(results: List<QuizResult>, navController: NavController) {
        val resultsJson = Uri.encode(Json.encodeToString(QuizResults(results)))
        navController.navigate(
            route = "${NavigationConstants.QUIZ_RESULTS_SCREEN}/$resultsJson"
        ) {
            popUpTo(NavigationConstants.QUESTION_SCREEN) {
                inclusive = true
            }
        }
    }

    private fun backToCategoriesInclusive(navController: NavController) {
        navController.navigate(NavigationConstants.CATEGORIES_SCREEN) {
            popUpTo(NavigationConstants.QUIZ_RESULTS_SCREEN) {
                inclusive = true
            }
        }
    }

    private fun deserializeQuizResults(backStackEntry: NavBackStackEntry): List<QuizResult> =
        backStackEntry.arguments?.let {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(NavigationConstants.QUIZ_RESULTS_ARG, QuizResults::class.java)
            } else {
                it.getParcelable(NavigationConstants.QUIZ_RESULTS_ARG)
            }
        }?.results ?: emptyList()
}
