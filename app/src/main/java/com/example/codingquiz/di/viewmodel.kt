package com.example.codingquiz.di

import com.example.codingquiz.data.domain.QuizResult
import com.example.codingquiz.viewmodel.CategoryViewModel
import com.example.codingquiz.viewmodel.GivenAnswerViewModel
import com.example.codingquiz.viewmodel.QuestionViewModel
import com.example.codingquiz.viewmodel.QuizResultsViewModel
import com.example.codingquiz.viewmodel.StatsViewModel
import com.example.codingquiz.viewmodel.TimerViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import kotlin.time.Duration

val viewModelModule = module {
    viewModelOf(::CategoryViewModel)
    viewModelOf(::QuestionViewModel)
    viewModelOf(::GivenAnswerViewModel)
    viewModelOf(::StatsViewModel)

    viewModel { (quizResults: List<QuizResult>) ->
        QuizResultsViewModel(quizResults)
    }

    viewModel { (timeout: Duration) ->
        TimerViewModel(timeout)
    }
}
