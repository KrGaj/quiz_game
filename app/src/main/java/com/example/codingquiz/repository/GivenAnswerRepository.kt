package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.AnswerDao
import com.example.codingquiz.data.database.entity.AnswerEntity
import com.example.codingquiz.data.domain.GivenAnswer

class GivenAnswerRepository(
    private val answerDao: AnswerDao,
) {
    suspend fun insert(
        answer: GivenAnswer,
    ) = answerDao.insert(
        AnswerEntity(
            question = answer.question.id,
            correct = answer.correct,
        )
    )
}