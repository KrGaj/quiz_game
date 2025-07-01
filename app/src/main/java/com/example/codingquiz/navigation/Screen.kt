package com.example.codingquiz.navigation

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import com.example.codingquiz.R
import com.example.codingquiz.util.navtype.CategoryNavType
import com.example.codingquiz.util.navtype.QuizSummaryNavType

sealed class Screen(
    val routeBase: String,
    val navArgs: List<NamedNavArgument> = emptyList(),
    // FIXME
    @StringRes val resourceId: Int,
) {
    val route get() = if (navArgs.isEmpty()) {
        routeBase
    } else {
        mutableListOf(routeBase).apply {
            addAll(
                navArgs.map { "{${it.name}}" }
            )
        }.joinToString(separator = "/")
    }

    data object Categories : Screen(
        routeBase = "Categories",
        resourceId = R.string.navigation_categories_label,
    )

    data object Question : Screen(
        routeBase = "Question",
        navArgs = listOf(
            navArgument("category") {
                type = CategoryNavType
            },
        ),
        resourceId = R.string.navigation_question_label,
    )

    data object QuizSummary : Screen(
        routeBase = "QuizSummary",
        navArgs = listOf(
            navArgument("quizResults") {
                type = QuizSummaryNavType
            },
        ),
        resourceId = R.string.navigation_results_label,
    )

    data object Statistics : Screen(
        routeBase = "Statistics",
        resourceId = R.string.navigation_stats_label,
    )
}
