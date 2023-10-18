package com.example.codingquiz.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "answers",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question"),
        ),
    ]
)
data class AnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val question: Int,
    val correct: Boolean,
)
