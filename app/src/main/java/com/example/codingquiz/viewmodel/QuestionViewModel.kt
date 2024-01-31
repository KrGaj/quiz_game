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
    private val _question = MutableStateFlow(
        value = DEFAULT_QUESTION,
    )
    val question get() = _question.asStateFlow()

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
            _question.value = questionWithIndex.value
            _questionNumber.value = questionWithIndex.index + 1
        }
    }

    companion object {
        private const val QUESTION_COUNT = 5
        val TIMEOUT = 30.seconds
        val DEFAULT_QUESTION = Question(
            id = 0,
            categoryId = 0,
            text = "Questions not loaded yet",
            answers = emptyList(),
        )
    }
}