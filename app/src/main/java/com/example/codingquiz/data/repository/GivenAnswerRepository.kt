package com.example.codingquiz.data.repository

import com.example.codingquiz.data.domain.GivenAnswer

fun interface GivenAnswerRepository {
    suspend fun insertAnswer(
        answer: GivenAnswer,
    ) : Long
}
