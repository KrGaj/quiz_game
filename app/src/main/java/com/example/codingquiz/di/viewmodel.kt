package com.example.codingquiz.di

import com.example.codingquiz.viewmodel.CategoryViewModel
import com.example.codingquiz.viewmodel.GivenAnswerViewModel
import com.example.codingquiz.viewmodel.QuestionViewModel
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import com.example.codingquiz.viewmodel.StatsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::CategoryViewModel)
    viewModelOf(::QuestionViewModel)
    viewModelOf(::GivenAnswerViewModel)
    viewModelOf(::QuizResultsViewModel)
    viewModelOf(::StatsViewModel)
}
