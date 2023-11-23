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

    private val _answeredQuestionsCount = MutableStateFlow(0)
    val answeredQuestionsCount get() = _answeredQuestionsCount.asStateFlow()

    private val _answersCount = MutableStateFlow(0)
    val answersCount get() = _answersCount.asStateFlow()

    fun getMostAnsweredCategories() {
        viewModelScope.launch {
            _categoryStats.value = statsRepository.getMostAnsweredCategories(CATEGORIES_COUNT)
        }
    }

    fun getAnsweredQuestionsCount() {
        viewModelScope.launch {
            _answeredQuestionsCount.value = statsRepository.getAnsweredQuestionsCount()
        }
    }

    fun getAllAnswersCount() {
        viewModelScope.launch {
            _answersCount.value = statsRepository.getAllAnswersCount()
        }
    }

    private

    companion object {
        private const val CATEGORIES_COUNT = 3
    }
}