package com.example.codingquiz.data.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class QuizResult(
    val questionText: String,
    val isAnswerCorrect: Boolean,
) : Parcelable

@Serializable
@Parcelize
data class QuizResults(
    val results: List<QuizResult>,
) : Parcelable
