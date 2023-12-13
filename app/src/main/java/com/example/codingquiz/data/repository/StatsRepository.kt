package com.example.codingquiz.data.repository

import com.example.codingquiz.data.domain.AnsweredQuestionsCountStats
import com.example.codingquiz.data.domain.CategoryStats
import com.example.codingquiz.data.domain.CorrectAnswersStats

interface StatsRepository {
    suspend fun getMostAnsweredCategories(
        count: Int,
    ): List<CategoryStats>

    suspend fun getAnsweredQuestionsCount(): AnsweredQuestionsCountStats

    suspend fun getCorrectAnswersCount(): CorrectAnswersStats
}
