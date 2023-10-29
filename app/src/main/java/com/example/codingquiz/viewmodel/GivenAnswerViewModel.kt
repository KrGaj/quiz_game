package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.repository.GivenAnswerRepository
import kotlinx.coroutines.launch

class GivenAnswerViewModel(
    private val givenAnswerRepository: GivenAnswerRepository,
) : ViewModel() {
    fun addAnswer(answer: GivenAnswer, callback: () -> Unit) {
        viewModelScope.launch {
            givenAnswerRepository.insert(answer)
            callback()
        }
    }
}