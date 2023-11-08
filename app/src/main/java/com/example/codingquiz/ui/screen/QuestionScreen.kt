package com.example.codingquiz.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.data.domain.PossibleAnswer
import com.example.codingquiz.data.domain.Question
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.GivenAnswerViewModel
import com.example.codingquiz.viewmodel.QuestionViewModel
import org.koin.androidx.compose.koinViewModel

class AnswerState {
    var isAnyAnswerChosen: Boolean by mutableStateOf(false)
    var isTimeOut: Boolean by mutableStateOf(false)
}

@Composable
fun QuestionScreen(
    questionViewModel: QuestionViewModel = koinViewModel(),
    givenAnswerViewModel: GivenAnswerViewModel = koinViewModel(),
    categoryId: Int?,
    onBackPressed: () -> Unit,
    navigateToResults: (List<QuizResult>) -> Unit,
) {
    val question by questionViewModel.question.collectAsState()
    val answerState = remember {
        AnswerState()
    }
    val timeLeft by questionViewModel.timeLeft.collectAsState()
    val answerAddCallback = {
        if (questionViewModel.isQuestionLast()) {
            navigateToResults(givenAnswerViewModel.quizResults)
        } else {
            answerState.isAnyAnswerChosen = false
            answerState.isTimeOut = false
            questionViewModel.nextQuestion()
        }
    }

    BackHandler {
        onBackPressed()
    }

    CodingQuizTheme {
        LaunchedEffect(Unit) { questionViewModel.fetchQuestions(categoryId) }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            QuestionText(question)
            AnswersGrid(
                answers = question.answers,
                { answerState.isAnyAnswerChosen },
                { answerState.isTimeOut },
            ) {
                if (!answerState.isAnyAnswerChosen) {
                    answerState.isAnyAnswerChosen = true
                    givenAnswerViewModel.addAnswer(
                        answer = GivenAnswer(
                            question = question,
                            correct = it.isCorrect,
                        ),
                        callback = answerAddCallback,
                    )
                }
            }
            Timer(timeLeft = timeLeft)
        }
    }

    if (timeLeft == 0L) {
        answerState.isTimeOut = true
        givenAnswerViewModel.addAnswer(
            answer = GivenAnswer(
                question = question,
                correct = false,
            ),
            callback = answerAddCallback,
        )
    }
}

@Composable
private fun QuestionText(question: Question) {
    CodingQuizTheme {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                text = question.text,
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 12.dp
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun AnswersGrid(
    answers: List<PossibleAnswer>,
    isAnyAnswerChosen: () -> Boolean,
    isTimeOut: () -> Boolean,
    onClick: (PossibleAnswer) -> Unit,
) {
    CodingQuizTheme {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(answers) {
                val shouldChangeColor = (isAnyAnswerChosen() || isTimeOut())

                val color = if (shouldChangeColor && it.isCorrect) Color.Green
                    else if (shouldChangeColor) Color.Red
                    else Color.Gray

                PossibleAnswer(
                    answer = it,
                    color,
                ) {
                    onClick(it)
                }
            }
        }
    }
}

@Composable
private fun PossibleAnswer(
    answer: PossibleAnswer,
    color: Color,
    onClick: () -> Unit,
) {
    CodingQuizTheme {
        FilledTonalButton(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(color),
            onClick = onClick,
        ) {
            Text(
                text = answer.text,
            )
        }
    }
}

@Composable
private fun Timer(timeLeft: Long) {
    CodingQuizTheme {
        val timeLeftText = pluralStringResource(
            id = R.plurals.question_time_left,
            count = timeLeft.toInt(),
            timeLeft.toInt(),
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = timeLeftText,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
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
    val isAnyAnswerChosen by remember { mutableStateOf(false) }
    val isTimeOut by remember { mutableStateOf(false) }

    CodingQuizTheme {
        AnswersGrid(
            isAnyAnswerChosen = { isAnyAnswerChosen },
            isTimeOut = { isTimeOut },
            answers = listOf(
                PossibleAnswer("Demo Answer 1", false),
                PossibleAnswer("Demo Answer 2", false),
                PossibleAnswer("Demo Answer 3", false),
                PossibleAnswer("Demo Answer 4", true),
            )
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewTimer() {
    CodingQuizTheme {
        Timer(timeLeft = 2137)
    }
}
