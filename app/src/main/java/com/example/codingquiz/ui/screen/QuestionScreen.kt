package com.example.codingquiz.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.QuestionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestionScreen(
    questionViewModel: QuestionViewModel = koinViewModel(),
    categoryId: Int?,
) {
    CodingQuizTheme {
        LaunchedEffect(Unit) { questionViewModel.fetchQuestions(categoryId) }
    }
}
