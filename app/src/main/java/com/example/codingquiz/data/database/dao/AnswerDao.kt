package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.codingquiz.data.database.entity.AnswerEntity

@Dao
interface AnswerDao {
    @Insert
    suspend fun insert(answer: AnswerEntity)
}