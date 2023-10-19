package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codingquiz.repository.QuestionRepository

class QuestionViewModel(
    private val questionRepository: QuestionRepository,
) : ViewModel() {

}