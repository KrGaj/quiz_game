package com.example.codingquiz.navigation

sealed class Dialog(
    val route: String,
    val navArg: String = "",
) {
    data object ExitQuiz : Dialog(
        route = "ExitQuiz",
        navArg = "quizResults",
    )

    data object ExitApp : Dialog(
        route = "ExitApp",
    )
}
