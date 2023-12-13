package com.example.codingquiz.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.codingquiz.data.database.entity.AnswerEntity

@Dao
fun interface AnswerDao {
    @Insert
    suspend fun insertAnswer(answer: AnswerEntity): Long
}