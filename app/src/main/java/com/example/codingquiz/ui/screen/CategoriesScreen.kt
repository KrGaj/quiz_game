package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Categories() {
    // TODO
}

@Preview(showBackground = true)
@Composable
fun CategoryGrid(
    categoryViewModel: CategoryViewModel = koinViewModel()
) {
    val categories = categoryViewModel.categories.collectAsState()

    CodingQuizTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = CategoryViewModel.COLUMNS_NUM),
        ) {
            items(categories.value) { item ->
                Card(modifier = Modifier.padding(16.dp)) {
                    Text(text = item.name)
                }
            }
        }
    }
}
