package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codingquiz.data.domain.PossibleAnswer
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
        QuestionText()
        AnswersGrid(answers = ) {

        }
    }
}

@Composable
fun QuestionText() {

}

@Composable
fun AnswersGrid(
    answers: List<PossibleAnswer>,
    onClick: (PossibleAnswer) -> Unit,
) {
    val isAnyAnswerChosen = remember {
        mutableStateOf(false)
    }

    CodingQuizTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(answers) {
                PossibleAnswer(
                    answer = it,
                    isAnyAnswerChosen = isAnyAnswerChosen,
                ) {
                    if (!isAnyAnswerChosen.value) {
                        isAnyAnswerChosen.value = true
                        onClick(it)
                    }
                }
            }
        }
    }
}

@Composable
fun PossibleAnswer(
    answer: PossibleAnswer,
    isAnyAnswerChosen: State<Boolean>,
    onClick: () -> Unit,
) {
    val color = if (isAnyAnswerChosen.value && answer.isCorrect) Color.Green
        else if (!answer.isCorrect) Color.Red
        else Color.Gray

    CodingQuizTheme {
        FilledTonalButton(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(color),
            onClick = onClick,
        ) {
            Text(text = answer.text)
        }
    }
}
