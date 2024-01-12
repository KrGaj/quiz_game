package com.example.codingquiz.data.repository

import com.example.codingquiz.data.database.dao.QuestionDao
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.data.domain.PossibleAnswer
import com.example.codingquiz.data.domain.Question

class QuestionRepositoryDefault(
    private val questionDao: QuestionDao,
) : QuestionRepository {
    override suspend fun getRandomQuestions(
        quantity: Int,
        category: Category,
    ): List<Question> {
        val questionsEntities = questionDao.getRandomQuestions(
            quantity,
            category.id,
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
                category.id,
                it.question.text,
                possibleAnswers.shuffled(),
            )
        }

        return questions.shuffled()
    }
}
