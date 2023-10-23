package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.repository.QuestionRepository
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val questionRepository: QuestionRepository,
) : ViewModel() {
    fun fetchQuestions(categoryId: Int?) {
        viewModelScope.launch {
            categoryId?.let {
                questionRepository.getRandom(
                    quantity = QUESTION_COUNT,
                    categoryId = it,
                )
            }
        }
    }

    companion object {
        private const val QUESTION_COUNT = 5
    }
}