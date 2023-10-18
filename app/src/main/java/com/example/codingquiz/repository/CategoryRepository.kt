package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.CategoryDao
import com.example.codingquiz.data.domain.Category

class CategoryRepository(
    private val categoryDao: CategoryDao,
) {
    suspend fun getAll(): List<Category> =
        categoryDao.getAll().map {
            Category(
                it.id,
                it.name,
            )
        }
}