package com.example.codingquiz.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "questions",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category"),
        ),
    ]
)
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "category") val category: Int,
    @ColumnInfo(name = "question_text") val text: String,
    @ColumnInfo(name = "answer_first") val answerFirst: String,
    @ColumnInfo(name = "answer_second") val answerSecond: String,
    @ColumnInfo(name = "answer_third") val answerThird: String,
    @ColumnInfo(name = "answer_fourth") val answerFourth: String,
    @ColumnInfo(name = "correct_answer") val correctAnswer: Int,
)
