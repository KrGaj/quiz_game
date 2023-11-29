package com.example.codingquiz.data.repository

import com.example.codingquiz.data.database.dao.CategoryDao
import com.example.codingquiz.data.domain.Category

class CategoryRepositoryDefault(
    private val categoryDao: CategoryDao,
) : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> =
        categoryDao.getAllCategories().map {
            Category(
                it.id,
                it.name,
            )
        }
}
