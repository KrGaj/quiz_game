package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codingquiz.data.domain.QuizResult

class QuizResultsViewModel(
    val quizResults: Array<QuizResult>,
) : ViewModel()
