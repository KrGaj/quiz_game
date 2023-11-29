package com.example.codingquiz.data.repository

import com.example.codingquiz.data.domain.Question

fun interface QuestionRepository {
    suspend fun getRandomQuestions(
        quantity: Int,
        categoryId: Int,
    ): List<Question>
}
