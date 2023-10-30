package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.data.domain.Question
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun QuizResultsScreen(
    quizResults: Array<QuizResult>,
    quizResultsViewModel: QuizResultsViewModel = koinViewModel { parametersOf(quizResults) },
) {
    CodingQuizTheme {
        QuizResultsScreen(quizResults = quizResultsViewModel.quizResults)
    }
}

@Composable
fun QuizResultsList(results: Array<GivenAnswer>) {
    CodingQuizTheme {
        LazyColumn {
            items(results) {
                QuizResult(it)
            }
        }
    }
}

@Composable
fun QuizResult(quizResult: GivenAnswer) {
    CodingQuizTheme {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = quizResult.question.text)
                Text(text = quizResult.correct.toString())
            }
        }
    }
}


@Preview
@Composable
private fun PreviewQuizResult() {
    QuizResult(quizResult = GivenAnswer(
        Question(0, 0, "Demo Question 1",
            listOf(com.example.codingquiz.data.domain.PossibleAnswer("A1", true))
        ),
        false,
    ))
}
