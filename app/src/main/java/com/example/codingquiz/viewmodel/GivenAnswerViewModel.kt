package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.data.repository.GivenAnswerRepository

class GivenAnswerViewModel(
    private val givenAnswerRepository: GivenAnswerRepository,
) : ViewModel() {
    private val _quizResults = mutableListOf<QuizResult>()
    val quizResults get() = _quizResults.toList()

    suspend fun addAnswer(answer: GivenAnswer) {
        givenAnswerRepository.insertAnswer(answer)
        _quizResults.add(QuizResult(answer.question.text, answer.correct))
    }
}