package com.example.codingquiz

import android.app.Application
import com.example.codingquiz.di.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dbModule)
        }
    }

    companion object {
        const val DATABASE_NAME = "quiz-database"
    }
}