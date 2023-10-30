package com.example.codingquiz.data.domain

import kotlinx.serialization.Serializable

@Serializable
data class QuizResult(
    val questionText: String,
    val isAnswerCorrect: Boolean,
)
