package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.ui.theme.CodingQuizTheme
import com.example.codingquiz.viewmodel.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryGrid(
    categoryViewModel: CategoryViewModel = koinViewModel(),
    onItemClicked: (Category) -> Unit,
) {
    val categories = categoryViewModel.categories.collectAsState()

    CodingQuizTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = CategoryViewModel.COLUMNS_NUM),
        ) {
            items(categories.value) { item ->
                Category(
                    name = item.name,
                ) {
                    onItemClicked(item)
                }
            }
        }
    }
}

@Composable
fun Category(
    name: String,
    onClick: () -> Unit = {},
) {
    CodingQuizTheme {
        FilledTonalButton(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            onClick = onClick,
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = typography.headlineMedium,
            )
        }
    }
}

@Preview
@Composable
fun PreviewCategory() {
    Box(modifier = Modifier.width(200.dp)) {
        Category(name = "Demo")
    }
}

//@Preview
//@Composable
//fun PreviewCategoryGrid() {
//
//}
