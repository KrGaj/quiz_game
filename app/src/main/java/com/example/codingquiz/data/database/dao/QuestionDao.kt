package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.codingquiz.data.database.entity.QuestionEntity

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions WHERE category = :categoryId ORDER BY RANDOM() LIMIT :quantity")
    suspend fun getRandom(
        quantity: Int,
        categoryId: Int,
    ): List<QuestionEntity>
}