package com.example.codingquiz.data.repository

import com.example.codingquiz.data.domain.Category

fun interface CategoryRepository {
    suspend fun getAllCategories(): List<Category>
}
