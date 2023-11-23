package com.example.codingquiz.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.common.TwoTextsRow
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun QuizResultsScreen(
    quizResults: List<QuizResult>,
    quizResultsViewModel: QuizResultsViewModel = koinViewModel { parametersOf(quizResults) },
    onBackPressed: () -> Unit,
    navigateToCategories: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }

    CodingQuizTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            with(quizResultsViewModel) {
                Score(
                    correctAnswers = correctAnswersCount,
                    allAnswers = answersCount,
                )
                QuizResultsList(results = this.quizResults)
            }

            FinishButton(navigateToCategories)
        }
    }
}

@Composable
private fun Score(
    correctAnswers: Int,
    allAnswers: Int,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val scoreText = stringResource(
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

@Composable
private fun QuizResultsList(results: List<QuizResult>) {
    LazyColumn {
        items(results) {
            QuizResult(it)
        }
    }
}

@Composable
private fun QuizResult(quizResult: QuizResult) {
    val isAnswerCorrectInfo = stringResource(
        id = if (quizResult.isAnswerCorrect) R.string.quiz_results_correct
        else R.string.quiz_results_wrong
    )

    TwoTextsRow(
        leftText = quizResult.questionText,
        rightText = isAnswerCorrectInfo,
    )
}

@Composable
private fun FinishButton(navigateToCategories: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        FilledTonalButton(onClick = navigateToCategories) {
            Text(text = stringResource(id = R.string.quiz_results_finish))
        }
    }
}


@Preview
@Composable
private fun PreviewScore() {
    CodingQuizTheme {
        Score(correctAnswers = 21, allAnswers = 37)
    }
}

@Preview
@Composable
private fun PreviewQuizResult() {
    CodingQuizTheme {
        QuizResult(
            quizResult = QuizResult(
                "Demo Question 1",
                false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFinishButton() {
    CodingQuizTheme {
        FinishButton {}
    }
}
