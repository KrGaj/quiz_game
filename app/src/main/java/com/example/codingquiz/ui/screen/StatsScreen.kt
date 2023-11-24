package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.AnsweredQuestionsCountStats
import com.example.codingquiz.data.domain.CategoryStats
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
    Column {
        LaunchedEffect(Unit) {
            statsViewModel.getMostAnsweredCategories()
            statsViewModel.getAnsweredQuestionsCount()
            statsViewModel.getCorrectAnswersCount()
        }

        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val categoryStats = statsViewModel.categoryStats.collectAsState()
            val answeredQuestionsStats = statsViewModel.answeredQuestionsCount.collectAsState()

            StatsLabel()
            CategoryStats(statsList = categoryStats.value)
            AnsweredQuestionsStats(stats = answeredQuestionsStats.value)
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
    LazyColumn {
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

@Preview(showBackground = true)
@Composable
private fun PreviewCategoryStats() {
    CodingQuizTheme {
        CategoryStats(statsList = categoryStats)
    }
}

@Preview
@Composable
private fun PreviewCategoryStatsRow() {
    CodingQuizTheme {
        CategoryStatsRow(stats = categoryStats[0])
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAnsweredQuestionsStats() {
    CodingQuizTheme {
        AnsweredQuestionsStats(stats = answeredQuestionsStats)
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
