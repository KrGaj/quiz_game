package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.Question
import com.example.codingquiz.data.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class QuestionViewModel(
    private val questionRepository: QuestionRepository,
) : ViewModel() {
    private lateinit var questionIterator: Iterator<IndexedValue<Question>>
    private val _questionWithIndex = MutableStateFlow(
        value = Question(0, 0, "Questions not loaded yet", emptyList()),
    )
    val questionWithIndex get() = _questionWithIndex.asStateFlow()

    private val _questionNumber = MutableStateFlow(0)
    val questionNumber = _questionNumber.asStateFlow()

    fun fetchQuestions(category: Category) {
        viewModelScope.launch {
            val questions = questionRepository.getRandomQuestions(
                quantity = QUESTION_COUNT,
                category = category,
            )

            questionIterator = questions.iterator().withIndex()
            nextQuestion()
        }
    }

    fun isQuestionLast() = !questionIterator.hasNext()

    fun nextQuestion() {
        if (!isQuestionLast()) {
            val questionWithIndex = questionIterator.next()
            _questionWithIndex.value = questionWithIndex.value
            _questionNumber.value = questionWithIndex.index + 1
        }
    }

    companion object {
        private const val QUESTION_COUNT = 5
        val TIMEOUT = 30.seconds
    }
}