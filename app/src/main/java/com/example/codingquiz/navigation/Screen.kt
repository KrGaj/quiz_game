package com.example.codingquiz.navigation

import androidx.annotation.StringRes
import com.example.codingquiz.R

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val navArg: String = "",
) {
    data object Categories : Screen(
        route = "Categories",
        resourceId = R.string.navigation_categories_label,
    )

    data object Question : Screen(
        route = "Question",
        resourceId = R.string.navigation_question_label,
        navArg = "category",
    )

    data object QuizSummary : Screen(
        route = "QuizSummary",
        resourceId = R.string.navigation_results_label,
        navArg = "quizResults",
    )

    data object Statistics : Screen(
        route = "Statistics",
        resourceId = R.string.navigation_stats_label,
    )
}
