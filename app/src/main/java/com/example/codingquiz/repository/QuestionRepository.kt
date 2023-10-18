package com.example.codingquiz.repository

import com.example.codingquiz.data.database.dao.QuestionDao
import com.example.codingquiz.data.database.entity.CategoryEntity
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.PossibleAnswer
import com.example.codingquiz.data.domain.Question

class QuestionRepository(
    private val questionDao: QuestionDao,
) {
    suspend fun getRandom(
        quantity: Int,
        category: Category,
    ): List<Question> {
        val categoryEntity = CategoryEntity(
            category.id,
            category.name,
        )

        val questionsEntities = questionDao.getRandom(
            quantity,
            categoryEntity,
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
                category,
                it.text,
                possibleAnswers,
            )
        }

        return questions
    }
}