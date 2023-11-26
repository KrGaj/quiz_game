package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.AnsweredQuestionsCountStats
import com.example.codingquiz.data.domain.CategoryStats
import com.example.codingquiz.data.domain.CorrectAnswersStats
import com.example.codingquiz.ui.common.HeaderTextLarge
import com.example.codingquiz.ui.common.HeaderTextMedium
import com.example.codingquiz.ui.common.TwoTextsRow
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.StatsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsScreen(
    statsViewModel: StatsViewModel = koinViewModel()
) {
    val categoryStats by statsViewModel.categoryStats
        .collectAsStateWithLifecycle()
    val answeredQuestionsStats by statsViewModel.answeredQuestionsCount
        .collectAsStateWithLifecycle()
    val correctAnswersStats by statsViewModel.correctAnswersCount
        .collectAsStateWithLifecycle()

    Column {
        LaunchedEffect(Unit) {
            statsViewModel.getMostAnsweredCategories()
            statsViewModel.getAnsweredQuestionsCount()
            statsViewModel.getCorrectAnswersCount()
        }

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            StatsLabel()
            CategoryStats(statsList = categoryStats)
            AnsweredQuestionsStats(stats = answeredQuestionsStats)
            CorrectAnswersStats(stats = correctAnswersStats)
        }
    }
}

@Composable
private fun StatsLabel() {
    HeaderTextLarge(
        text = stringResource(id = R.string.stats_header),
    )
}

@Composable
private fun CategoryStats(statsList: List<CategoryStats>) {
    Column {
        CategoryStatsLabel()
        CategoryStatsList(statsList = statsList)
    }
}

@Composable
private fun CategoryStatsLabel() {
    HeaderTextMedium(
        text = stringResource(id = R.string.stats_most_answered_header),
    )
}

@Composable
private fun CategoryStatsList(statsList: List<CategoryStats>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val statsListSorted = statsList.sortedByDescending { it.answersGiven }
        items(statsListSorted) {
            CategoryStatsRow(it)
        }
    }
}

@Composable
private fun CategoryStatsRow(stats: CategoryStats) {
    TwoTextsRow(
        leftText = stats.category.name,
        rightText = stats.answersGiven.toString(),
    )
}

@Composable
private fun AnsweredQuestionsStats(stats: AnsweredQuestionsCountStats) {
    Column {
        AnsweredQuestionsLabel()
        AnsweredQuestionsRow(stats = stats)
    }
}

@Composable
private fun AnsweredQuestionsLabel() {
    HeaderTextMedium(
        text = stringResource(id = R.string.answered_questions_header),
    )
}

@Composable
private fun AnsweredQuestionsRow(stats: AnsweredQuestionsCountStats) {
    TwoTextsRow(
        leftText = stringResource(
            id = R.string.answered_questions_msg,
        ),
        rightText = stringResource(
            id = R.string.answered_questions_count,
            stats.questionsAnswered,
            stats.allQuestions,
        ),
    )
}

@Composable
private fun CorrectAnswersStats(stats: CorrectAnswersStats) {
    Column {
        CorrectAnswersStatsLabel()
        CorrectAnswersStatsRow(stats = stats)
    }
}

@Composable
private fun CorrectAnswersStatsLabel() {
    HeaderTextMedium(text = stringResource(id = R.string.correct_answers_header))
}

@Composable
private fun CorrectAnswersStatsRow(stats: CorrectAnswersStats) {
    val percentage = if (stats.allAnswers != 0)
            (stats.correctAnswers.toDouble()/stats.allAnswers) * 100
    else 0.0

    TwoTextsRow(
        leftText = stringResource(id = R.string.correct_answers_msg),
        rightText = stringResource(
            id = R.string.correct_answers_count,
            stats.correctAnswers,
            stats.allAnswers,
            percentage,
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategoryStats() {
    CodingQuizTheme {
        CategoryStats(statsList = categoryStats)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAnsweredQuestionsStats() {
    CodingQuizTheme {
        AnsweredQuestionsStats(stats = answeredQuestionsStats)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCorrectAnswersStats() {
    CodingQuizTheme {
        CorrectAnswersStats(stats = correctAnswersStats)
    }
}

private val categoryStats = listOf(
    CategoryStats(
        com.example.codingquiz.data.domain.Category(0, "Demo1"),
        2137,
    ),
    CategoryStats(
        com.example.codingquiz.data.domain.Category(1, "Demo2"),
        21,
    ),
    CategoryStats(
        com.example.codingquiz.data.domain.Category(2, "Demo3"),
        37,
    ),
)

private val answeredQuestionsStats = AnsweredQuestionsCountStats(15, 20)

private val correctAnswersStats = CorrectAnswersStats(20, 25)
