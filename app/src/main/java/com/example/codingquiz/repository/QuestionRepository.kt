package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.QuestionDao
import com.example.codingquiz.data.domain.PossibleAnswer
import com.example.codingquiz.data.domain.Question

class QuestionRepository(
    private val questionDao: QuestionDao,
) {
    suspend fun getRandom(
        quantity: Int,
        categoryId: Int,
    ): List<Question> {
        val questionsEntities = questionDao.getRandom(
            quantity,
            categoryId,
        )

        val questions = questionsEntities.map {
            val possibleAnswers = listOf(
                it.answerFirst,
                it.answerSecond,
                it.answerThird,
                it.answerFourth,
            ).mapIndexed { index, answer ->
                PossibleAnswer(
                    answer,
                    index + 1 == it.correctAnswer
                )
            }

            Question(
                it.id,
                categoryId,
                it.text,
                possibleAnswers,
            )
        }

        return questions.shuffled()
    }
}