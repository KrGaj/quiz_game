package com.example.codingquiz.di

import com.example.codingquiz.repository.GivenAnswerRepository
import com.example.codingquiz.repository.CategoryRepository
import com.example.codingquiz.repository.QuestionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::CategoryRepository)
    singleOf(::QuestionRepository)
    singleOf(::GivenAnswerRepository)
}
