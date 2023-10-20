package com.example.codingquiz

import android.app.Application
import com.example.codingquiz.di.databaseModule
import com.example.codingquiz.di.repositoryModule
import com.example.codingquiz.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class QuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@QuizApplication)
            modules(databaseModule, repositoryModule, viewModelModule)
        }
    }
}