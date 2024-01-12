package com.example.codingquiz.data.repository

import com.example.codingquiz.data.database.dao.AnswerDao
import com.example.codingquiz.data.database.entity.AnswerEntity
import com.example.codingquiz.data.domain.GivenAnswer

class GivenAnswerRepositoryDefault(
    private val answerDao: AnswerDao,
) : GivenAnswerRepository {
    override suspend fun insertAnswer(
        answer: GivenAnswer,
    ) = answerDao.insertAnswer(
        AnswerEntity(
            question = answer.question.id,
            correct = answer.correct,
        )
    )
}