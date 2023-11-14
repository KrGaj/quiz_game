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
            val possibleAnswers = it.possibleAnswers.map { answer ->
                PossibleAnswer(
                    answer.answerText,
                    answer.correct,
                )
            }

            Question(
                it.question.id,
                categoryId,
                it.question.text,
                possibleAnswers.shuffled(),
            )
        }

        return questions.shuffled()
    }
}
