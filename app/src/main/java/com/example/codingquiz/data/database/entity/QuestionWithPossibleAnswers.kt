package com.example.codingquiz.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionWithPossibleAnswers(
    @Embedded val question: QuestionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "question",
    )
    val possibleAnswers: List<PossibleAnswerEntity>,
)
