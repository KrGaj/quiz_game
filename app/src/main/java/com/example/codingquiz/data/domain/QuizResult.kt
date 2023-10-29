package com.example.codingquiz.data.domain

import java.io.Serializable

data class QuizResult(
    val question: Question,
    val isAnswerCorrect: Boolean,
) : Serializable
