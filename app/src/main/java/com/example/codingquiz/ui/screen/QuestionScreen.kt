package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.data.domain.PossibleAnswer
import com.example.codingquiz.data.domain.Question
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.GivenAnswerViewModel
import com.example.codingquiz.viewmodel.QuestionViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuestionScreen(
    questionViewModel: QuestionViewModel = koinViewModel(),
    givenAnswerViewModel: GivenAnswerViewModel = koinViewModel(),
    categoryId: Int?,
) {
    val question = questionViewModel.question.collectAsState()

    CodingQuizTheme {
        LaunchedEffect(Unit) { questionViewModel.fetchQuestions(categoryId) }
        QuestionText(question.value)
        AnswersGrid(answers = question.value.answers) {
            givenAnswerViewModel.addAnswer(GivenAnswer(
                question = question.value,
                correct = it.isCorrect,
            ))
        }
    }
}

@Composable
fun QuestionText(question: Question) {
    CodingQuizTheme {
        Card(
            modifier = Modifier.padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                text = question.text,
                modifier = Modifier.padding(
                    horizontal = 24.dp,
                    vertical = 12.dp
                ).fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
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
        else if (isAnyAnswerChosen.value && !answer.isCorrect) Color.Red
        else Color.Gray

    CodingQuizTheme {
        FilledTonalButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(color),
            onClick = onClick,
        ) {
            Text(text = answer.text)
        }
    }
}


@Preview
@Composable
private fun PreviewQuestionText() {
    CodingQuizTheme {
        QuestionText(question = Question(0, 0,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                    "sed do eiusmod tempor incididunt ut labore et dolore " +
                    "magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                    "exercitation ullamco laboris nisi ut aliquip ex ea " +
                    "commodo consequat. Duis aute irure dolor in " +
                    "reprehenderit in voluptate velit esse cillum dolore " +
                    "eu fugiat nulla pariatur. Excepteur sint occaecat " +
                    "cupidatat non proident, sunt in culpa qui officia " +
                    "deserunt mollit anim id est laborum.",
            emptyList()))
    }
}

@Preview
@Composable
private fun PreviewAnswers() {
    CodingQuizTheme {
        AnswersGrid(
            answers = listOf(
                PossibleAnswer("Demo Answer 1", false),
                PossibleAnswer("Demo Answer 2", false),
                PossibleAnswer("Demo Answer 3", false),
                PossibleAnswer("Demo Answer 4", true),
            )
        ) {}
    }
}
