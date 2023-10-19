package com.example.codingquiz.di

import androidx.room.Room
import com.example.codingquiz.data.database.QuizDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            QuizDatabase::class.java,
            QuizDatabase.DATABASE_NAME,
        ).build()
    }

    single { get<QuizDatabase>().categoryDao() }
    single { get<QuizDatabase>().questionDao() }
    single { get<QuizDatabase>().answerDao() }
}
