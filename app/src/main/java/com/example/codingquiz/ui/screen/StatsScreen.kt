package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.R
import com.example.codingquiz.data.domain.CategoryStats
import com.example.codingquiz.ui.common.TwoTextsRow
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.StatsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatsScreen(
    statsViewModel: StatsViewModel = koinViewModel()
) {
    Column {
        val categoryStats = statsViewModel.categoryStats.collectAsState()

        CodingQuizTheme {
            StatsLabel()
            CategoryStatsLabel()
            CategoryStatsList(statsList = categoryStats.value)
        }
    }
}

@Composable
private fun StatsLabel() {
    Text(
        text = stringResource(id = R.string.stats_header),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.padding(horizontal = 8.dp),
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
    Text(
        text = stringResource(id = R.string.stats_most_answered_header),
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier.padding(8.dp),
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
