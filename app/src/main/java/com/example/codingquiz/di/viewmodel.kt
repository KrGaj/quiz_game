package com.example.codingquiz.di

import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.viewmodel.CategoryViewModel
import com.example.codingquiz.viewmodel.GivenAnswerViewModel
import com.example.codingquiz.viewmodel.QuestionViewModel
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CategoryViewModel(get()) }
    viewModel { QuestionViewModel(get()) }
    viewModel { GivenAnswerViewModel(get()) }
    viewModel { (results: List<QuizResult>) -> QuizResultsViewModel(results) }
}
