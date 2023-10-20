package com.example.codingquiz.data.database.entity

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "question") val question: Int,
    @ColumnInfo(name = "correct") val correct: Boolean,
)
