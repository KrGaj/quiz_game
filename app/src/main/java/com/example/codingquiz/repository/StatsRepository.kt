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
                    it.first.id,
                    it.first.name,
                ),
                it.second,
            )
        }

        return categories
    }
}
