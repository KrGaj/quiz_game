package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.Question
import com.example.codingquiz.repository.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val questionRepository: QuestionRepository,
) : ViewModel() {
    private var questions = emptyList<Question>()
    private var questionIterator = questions.iterator()
    private val _question = MutableStateFlow(Question(0, 0, "Questions not loaded yet", emptyList()))
    val question get() = _question.asStateFlow()

    fun fetchQuestions(categoryId: Int?) {
        viewModelScope.launch {
            categoryId?.let {
                questions = questionRepository.getRandom(
                    quantity = QUESTION_COUNT,
                    categoryId = it,
                )
            }

            questionIterator = questions.iterator()
            _question.value = questionIterator.next()
        }
    }

    fun isQuestionLast() = !questionIterator.hasNext()

    fun nextQuestion() {
        if (!isQuestionLast()) {
            _question.value = questionIterator.next()
        }
    }

    companion object {
        private const val QUESTION_COUNT = 5
    }
}