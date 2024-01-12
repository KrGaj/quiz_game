package com.example.codingquiz.di

import com.example.codingquiz.data.repository.CategoryRepository
import com.example.codingquiz.data.repository.CategoryRepositoryDefault
import com.example.codingquiz.data.repository.GivenAnswerRepository
import com.example.codingquiz.data.repository.GivenAnswerRepositoryDefault
import com.example.codingquiz.data.repository.QuestionRepository
import com.example.codingquiz.data.repository.QuestionRepositoryDefault
import com.example.codingquiz.data.repository.StatsRepository
import com.example.codingquiz.data.repository.StatsRepositoryDefault
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::CategoryRepositoryDefault) bind CategoryRepository::class
    singleOf(::QuestionRepositoryDefault) bind QuestionRepository::class
    singleOf(::GivenAnswerRepositoryDefault) bind GivenAnswerRepository::class
    singleOf(::StatsRepositoryDefault) bind StatsRepository::class
}
