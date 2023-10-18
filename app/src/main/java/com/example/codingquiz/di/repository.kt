package com.example.codingquiz.di

import com.example.codingquiz.repository.AnswerRepository
import com.example.codingquiz.repository.CategoryRepository
import com.example.codingquiz.repository.QuestionRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CategoryRepository(get()) }
    single { QuestionRepository(get()) }
    single { AnswerRepository(get()) }
}
