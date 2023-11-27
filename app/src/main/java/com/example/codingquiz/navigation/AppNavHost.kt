package com.example.codingquiz.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.codingquiz.util.navtype.CategoryNavType
import com.example.codingquiz.util.navtype.QuizResultNavType
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.data.domain.QuizResults
import com.example.codingquiz.ui.dialogs.ExitAppDialog
import com.example.codingquiz.ui.dialogs.ExitQuizDialog
import com.example.codingquiz.ui.screen.CategoriesScreen
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.screen.QuizSummaryScreen
import com.example.codingquiz.ui.screen.StatsScreen
import com.example.codingquiz.util.findActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Categories.route,
    ) {
        configureCategoriesScreenRoute(
            navGraphBuilder = this,
            navController,
        )

        configureQuestionScreenRoute(
            navGraphBuilder = this,
            navController,
        )

        configureQuizResultsScreen(
            navGraphBuilder = this,
            navController,
        )

        configureStatsScreen(
            navGraphBuilder = this,
        )

        configureExitQuizDialog(
            navGraphBuilder = this,
            navController,
        )

        configureExitAppDialog(
            navGraphBuilder = this,
            navController,
        )
    }
}

private fun configureCategoriesScreenRoute(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.composable(
        route = Screen.Categories.route,
    ) {
        CategoriesScreen(
            onBackPressed = {
                navController.navigate(Dialog.ExitApp.route)
            }
        ) {
            navController.navigate(
                route = "${Screen.Question.route}/${encodeCategory(it)}",
            ) {
                popUpTo(
                    Screen.Categories.route,
                ) {
                    inclusive = true
                }
            }
        }
    }
}

private fun configureQuestionScreenRoute(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.composable(
        route = "${Screen.Question.route}/{${Screen.Question.navArg}}",
        arguments = listOf(navArgument(Screen.Question.navArg) {
            type = CategoryNavType()
        }),
    ) { backStackEntry ->
        QuestionScreen(
            category = deserializeCategory(backStackEntry),
            onBackPressed = {
                val resultsJson = encodeQuizResults(it)
                navController.navigate("${Dialog.ExitQuiz.route}/$resultsJson")
            },
            navigateToResults = { navigateToResultsScreen(it, navController) },
        )
    }
}

private fun configureQuizResultsScreen(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.composable(
        route = "${Screen.QuizSummary.route}/{${Screen.QuizSummary.navArg}}",
        arguments = listOf(navArgument(Screen.QuizSummary.navArg) {
            type = QuizResultNavType()
        }),
    ) { backStackEntry ->
        val results = deserializeQuizResults(backStackEntry)

        QuizSummaryScreen(
            quizResults = results,
            onBackPressed = { backToCategoriesInclusive(navController) },
            navigateToCategories = { backToCategoriesInclusive(navController) },
        )
    }
}

private fun configureStatsScreen(
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = Screen.Statistics.route,
    ) {
        StatsScreen()
    }
}

private fun configureExitQuizDialog(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.dialog(
        route = "${Dialog.ExitQuiz.route}/{${Dialog.ExitQuiz.navArg}}",
        dialogProperties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
        arguments = listOf(navArgument(Dialog.ExitQuiz.navArg) {
            type = QuizResultNavType()
        })
    ) { backStackEntry ->
        val results = deserializeQuizResults(backStackEntry)

        ExitQuizDialog(
            onDismissRequest = { navController.popBackStack() },
            onConfirmation = {
                if (results.isEmpty()) {
                    navController.navigate(Screen.Categories.route) {
                        popUpTo(Screen.Question.route) {
                            inclusive = true
                        }
                    }
                } else {
                    navigateToResultsScreen(results, navController)
                }
            }
        )
    }
}

private fun configureExitAppDialog(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.dialog(
        route = Dialog.ExitApp.route,
        dialogProperties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        val context = LocalContext.current

        ExitAppDialog(
            onDismissRequest = { navController.popBackStack() },
            onConfirmation = {
                context.findActivity().finish()
            }
        )
    }
}

private fun navigateToResultsScreen(
    results: List<QuizResult>,
    navController: NavController,
) {
    val resultsJson = encodeQuizResults(results)
    navController.navigate(
        route = "${Screen.QuizSummary.route}/$resultsJson"
    ) {
        popUpTo(Screen.Question.route) {
            inclusive = true
        }
    }
}

private fun backToCategoriesInclusive(navController: NavController) {
    navController.navigate(Screen.Categories.route) {
        popUpTo(Screen.QuizSummary.route) {
            inclusive = true
        }
    }
}

private fun encodeCategory(
    category: Category,
): String = Uri.encode(Json.encodeToString(category))

private fun deserializeCategory(
    backStackEntry: NavBackStackEntry,
): Category? =
    backStackEntry.arguments?.let {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            it.getParcelable(Screen.Question.navArg, Category::class.java)
        } else {
            it.getParcelable(Screen.Question.navArg)
        }
    }

private fun encodeQuizResults(
    results: List<QuizResult>,
): String = Uri.encode(Json.encodeToString(QuizResults(results)))

private fun deserializeQuizResults(
    backStackEntry: NavBackStackEntry,
): List<QuizResult> =
    backStackEntry.arguments?.let {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            it.getParcelable(Screen.QuizSummary.navArg, QuizResults::class.java)
        } else {
            it.getParcelable(Screen.QuizSummary.navArg)
        }
    }?.results ?: emptyList()
