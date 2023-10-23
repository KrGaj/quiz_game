package com.example.codingquiz.data.domain

data class Question(
    val id: Int,
    val categoryId: Int,
    val text: String,
    val answers: List<PossibleAnswer>,
)
