package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.codingquiz.data.database.entity.QuestionWithPossibleAnswers

@Dao
fun interface QuestionDao {
    @Transaction
    @Query("SELECT * FROM questions WHERE category = :categoryId ORDER BY RANDOM() LIMIT :quantity")
    suspend fun getRandomQuestions(
        quantity: Int,
        categoryId: Int,
    ): List<QuestionWithPossibleAnswers>
}
