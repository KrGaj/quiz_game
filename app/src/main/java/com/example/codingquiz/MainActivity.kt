package com.example.codingquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.codingquiz.di.databaseModule
import com.example.codingquiz.di.repositoryModule
import com.example.codingquiz.di.viewModelModule
import com.example.codingquiz.navigation.AppNavHost
import com.example.codingquiz.ui.theme.CodingQuizTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KoinApplication(application = {
                androidContext(application)
                androidLogger()

                modules(
                    listOf(
                        databaseModule,
                        repositoryModule,
                        viewModelModule,
                    )
                )
            }) {
                CodingQuizTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        AppNavHost(navController)
                    }
                }
            }
        }
    }
}
