package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.CategoryDao
import com.example.codingquiz.data.domain.Category

class CategoryRepository(
    private val categoryDao: CategoryDao,
) {
    suspend fun getAllCategories(): List<Category> =
        categoryDao.getAllCategories().map {
            Category(
                it.id,
                it.name,
            )
        }
}