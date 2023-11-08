package com.example.codingquiz.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun CategoriesScreen(
    categoryViewModel: CategoryViewModel = koinViewModel(),
    navigateOnItemClicked: (Category) -> Unit,
) {
    val categories = categoryViewModel.categories.collectAsState()

    CodingQuizTheme {
        CategoryGrid(categories = categories.value, navigateOnItemClicked)
    }
}

@Composable
private fun CategoryGrid(
    categories: List<Category>,
    onItemClicked: (Category) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = CategoryViewModel.COLUMNS_NUM),
    ) {
        items(categories) { item ->
            Category(
                name = item.name,
            ) {
                onItemClicked(item)
            }
        }
    }
}

@Composable
private fun Category(
    name: String,
    onClick: () -> Unit = {},
) {
    FilledTonalButton(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = typography.headlineMedium,
        )
    }
}

@Preview
@Composable
private fun PreviewCategory() {
    CodingQuizTheme {
        Box(modifier = Modifier.width(200.dp)) {
            Category(name = "Demo")
        }
    }
}

@Preview
@Composable
private fun PreviewCategoryGrid() {
    CodingQuizTheme {
        CategoryGrid(
            listOf(
                Category(0, "Category 1"),
                Category(0, "Category 2"),
                Category(0, "Category 3"),
                Category(0, "Category 4"),
            )
        ) {}
    }
}
