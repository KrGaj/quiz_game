package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.StatsDao
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.CategoryStats

class StatsRepository(
    private val statsDao: StatsDao,
) {
    suspend fun getMostAnsweredCategories(count: Int): List<CategoryStats> {
        val categoryEntities = statsDao.getMostAnsweredCategories(count)

        val categories = categoryEntities.map {
            CategoryStats(
                Category(
                    it.key.id,
                    it.key.name,
                ),
                it.value,
            )
        }

        return categories
    }

    suspend fun getAnsweredQuestionsCount() = statsDao.getAnsweredQuestionsCount()

    suspend fun getAllAnswersCount() = statsDao.getAllAnswersCount()
}
