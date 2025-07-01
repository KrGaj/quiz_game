package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.codingquiz.data.database.entity.PossibleAnswerEntity
import com.example.codingquiz.data.database.entity.QuestionEntity

@Dao
fun interface QuestionDao {
    @Transaction
    @Query(
        "SELECT * FROM (SELECT * FROM questions WHERE category = :categoryId ORDER BY RANDOM() LIMIT :quantity) AS selected_questions " +
                "JOIN possible_answers ON possible_answers.question = selected_questions.id"
    )
    suspend fun getRandomQuestions(
        categoryId: Int,
        quantity: Int,
    ): Map<QuestionEntity, List<PossibleAnswerEntity>>
}
