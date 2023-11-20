package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.CategoryStats
import com.example.codingquiz.repository.StatsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsViewModel(
    private val statsRepository: StatsRepository,
) : ViewModel() {
    private val _categoryStats = MutableStateFlow<List<CategoryStats>>(emptyList())
    val categoryStats get() = _categoryStats.asStateFlow()

    fun getMostAnsweredCategories() {
        viewModelScope.launch {
            _categoryStats.value = statsRepository.getMostAnsweredCategories(CATEGORIES_COUNT)
        }
    }

    companion object {
        private const val CATEGORIES_COUNT = 3
    }
}