package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.codingquiz.data.database.entity.CategoryEntity

@Dao
interface StatsDao {
    @Query(
        "SELECT *, Count(category_name) FROM categories LEFT JOIN " +
                "(SELECT * FROM questions INNER JOIN answers ON answers.question=questions.id) " +
                "AS questions_with_answers ON categories.id=questions_with_answers.category " +
                "GROUP BY category_name ORDER BY Count(category_name) DESC LIMIT :count"
    )
    suspend fun getMostAnsweredCategories(count: Int): List<Pair<CategoryEntity, Int>>
}
