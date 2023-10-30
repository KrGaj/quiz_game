package com.example.codingquiz.data.domain

import java.io.Serializable

data class GivenAnswer(
    val question: Question,
    val correct: Boolean,
) : Serializable
