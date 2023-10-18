package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.codingquiz.data.database.entity.CategoryEntity
import com.example.codingquiz.data.database.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT :quantity")
    suspend fun getRandom(
        quantity: Int,
        category: CategoryEntity,
    ): List<QuestionEntity>
}