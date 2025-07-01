package com.example.codingquiz.navigation

sealed class Dialog(
    val route: String,
) {
    data object ExitQuiz : Dialog(
        route = "ExitQuiz",
    ) {
        const val navArg = "quizResults"
    }
}
