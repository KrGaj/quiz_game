package com.example.codingquiz.navigation

import android.net.Uri
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.data.domain.QuizSummary
import com.example.codingquiz.ui.dialogs.ExitDialog
import com.example.codingquiz.ui.screen.CategoriesScreen
import com.example.codingquiz.ui.screen.QuestionScreen
import com.example.codingquiz.ui.screen.QuizSummaryScreen
import com.example.codingquiz.ui.screen.StatsScreen
import com.example.codingquiz.util.navtype.CategoryNavType
import com.example.codingquiz.util.navtype.QuizSummaryNavType
import kotlinx.serialization.json.Json

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Categories.routeBase,
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
    }
}

private fun configureCategoriesScreenRoute(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.composable(
        route = Screen.Categories.routeBase,
    ) {
        CategoriesScreen {
            navController.navigate(
                route = "${Screen.Question.routeBase}/${encodeCategory(it)}",
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
        route = Screen.Question.route,
        arguments = Screen.Question.navArgs,
    ) { backStackEntry ->
        val snackbarHostState = remember {
            SnackbarHostState()
        }

        deserializeCategory(backStackEntry)?.let { category ->
            QuestionScreen(
                category = category,
                onBackPressed = {
                    val resultsJson = encodeQuizResults(it)
                    navController.navigate("${Dialog.ExitQuiz.route}/$resultsJson")
                },
                navigateToResults = { navigateToResultsScreen(it, navController) },
            )
        } ?: run {
            val message = stringResource(id = R.string.navigation_question_error)

            LaunchedEffect(Unit) {
                snackbarHostState.showSnackbar(message)
            }
        }
    }
}

private fun configureQuizResultsScreen(
    navGraphBuilder: NavGraphBuilder,
    navController: NavController,
) {
    navGraphBuilder.composable(
        route = Screen.QuizSummary.route,
        arguments = Screen.QuizSummary.navArgs,
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
        route = Screen.Statistics.routeBase,
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
            type = QuizSummaryNavType
        })
    ) { backStackEntry ->
        val results = deserializeQuizResults(backStackEntry)

        ExitDialog(
            message = stringResource(id = R.string.quiz_exit_message),
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

private fun navigateToResultsScreen(
    results: List<QuizResult>,
    navController: NavController,
) {
    val resultsJson = encodeQuizResults(results)
    navController.navigate(
        route = "${Screen.QuizSummary.routeBase}/$resultsJson"
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
        val categoryKey = Screen.Question.navArgs.find { navArg ->
            navArg.argument.type is CategoryNavType
        } ?: return@let null

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            it.getParcelable(categoryKey.name, Category::class.java)
        } else {
            it.getParcelable(categoryKey.name)
        }
    }

private fun encodeQuizResults(
    results: List<QuizResult>,
): String = Uri.encode(Json.encodeToString(QuizSummary(results)))

private fun deserializeQuizResults(
    backStackEntry: NavBackStackEntry,
): List<QuizResult> =
    backStackEntry.arguments?.let {
        val resultsKey = Screen.QuizSummary.navArgs.find { navArg ->
            navArg.argument.type is QuizSummaryNavType
        } ?: return@let null

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            it.getParcelable(resultsKey.name, QuizSummary::class.java)
        } else {
            it.getParcelable(resultsKey.name)
        }
    }?.results ?: emptyList()
