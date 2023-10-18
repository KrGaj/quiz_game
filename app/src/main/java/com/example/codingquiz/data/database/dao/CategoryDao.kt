package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.codingquiz.data.database.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntity>
}