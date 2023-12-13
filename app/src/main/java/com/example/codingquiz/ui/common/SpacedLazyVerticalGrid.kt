package com.example.codingquiz.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacedLazyVerticalGrid(
    modifier: Modifier = Modifier,
    columns: GridCells,
    content: LazyGridScope.() -> Unit,
) {
    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
        columns = columns,
        content = content,
    )
}
