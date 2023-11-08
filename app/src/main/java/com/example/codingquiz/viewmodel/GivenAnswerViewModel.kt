package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.GivenAnswer
import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.repository.GivenAnswerRepository
import com.example.codingquiz.util.Timer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class GivenAnswerViewModel(
    private val givenAnswerRepository: GivenAnswerRepository,
) : ViewModel() {
    private val _quizResults = mutableListOf<QuizResult>()
    val quizResults get() = _quizResults.toList()

    fun addAnswer(answer: GivenAnswer, callback: () -> Unit) {
        Timer.stop()
        viewModelScope.launch {
            delay(DELAY)
            givenAnswerRepository.insert(answer)
            _quizResults.add(QuizResult(answer.question.text, answer.correct))
            callback()
        }
    }

    companion object {
        private val DELAY = 1.seconds
    }
}