package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.MapColumn
import androidx.room.Query
import com.example.codingquiz.data.database.entity.CategoryEntity

@Dao
interface StatsDao {
    @Query(
        "SELECT categories.id, categories.category_name, Count(questions_with_answers.id) " +
                "AS answers_count FROM categories LEFT JOIN (SELECT * FROM questions " +
                "INNER JOIN answers ON answers.question=questions.id) AS questions_with_answers " +
                "ON categories.id=questions_with_answers.category GROUP BY category_name " +
                "ORDER BY Count(questions_with_answers.id) DESC LIMIT :count"
    )
    suspend fun getMostAnsweredCategories(
        count: Int,
    ): Map<CategoryEntity, @MapColumn("answers_count") Int>
}
