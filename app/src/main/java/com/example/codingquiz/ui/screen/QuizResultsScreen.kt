package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun QuizResultsScreen(
    quizResults: List<QuizResult>,
    quizResultsViewModel: QuizResultsViewModel = koinViewModel { parametersOf(quizResults) },
) {
    CodingQuizTheme {
        Column {
            Score(
                correctAnswers = quizResultsViewModel.correctAnswersCount,
                allAnswers = quizResultsViewModel.answersCount,
            )
            QuizResultsList(results = quizResultsViewModel.quizResults)
            Column {

            }
        }
    }
}

@Composable
private fun Score(
    correctAnswers: Int,
    allAnswers: Int,
) {
    CodingQuizTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val scoreText = LocalContext.current.getString(
                R.string.quiz_results_score,
                correctAnswers,
                allAnswers,
            )
            
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = scoreText,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun QuizResultsList(results: List<QuizResult>) {
    CodingQuizTheme {
        LazyColumn {
            items(results) {
                QuizResult(it)
            }
        }
    }
}

@Composable
private fun QuizResult(quizResult: QuizResult) {
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
                Text(
                    modifier = Modifier.weight(0.7f),
                    text = quizResult.questionText,
                )

                val answerResult = LocalContext.current.getString(
                    if (quizResult.isAnswerCorrect) R.string.quiz_results_correct
                    else R.string.quiz_results_wrong
                )

                Text(text = answerResult)
            }
        }
    }
}

@Composable
private fun FinishButton() {

}


@Preview
@Composable
private fun PreviewScore() {
    Score(correctAnswers = 21, allAnswers = 37)
}

@Preview
@Composable
private fun PreviewQuizResult() {
    QuizResult(quizResult = QuizResult(
        "Demo Question 1",
        false,
        )
    )
}
