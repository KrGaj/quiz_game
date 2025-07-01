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
        val questionsMap = questionDao.getRandomQuestions(
            category.id,
            quantity,
        )

        val questions = questionsMap.map {
            val possibleAnswers = it.value.map { answer ->
                PossibleAnswer(
                    answer.answerText,
                    answer.correct,
                )
            }

            Question(
                it.key.id,
                category.id,
                it.key.text,
                possibleAnswers.shuffled(),
            )
        }

        return questions.shuffled()
    }
}
