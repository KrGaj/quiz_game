package com.example.codingquiz.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "possible_answers",
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("question"),
        ),
    ],
)
data class PossibleAnswerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "question", index = true) val question: Int,
    @ColumnInfo(name = "answer_text") val answerText: String,
    @ColumnInfo(name = "correct") val correct: Boolean,
)
