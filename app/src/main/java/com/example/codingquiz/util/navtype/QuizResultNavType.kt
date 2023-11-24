package com.example.codingquiz.util.navtype

import android.os.Bundle
import androidx.navigation.NavType
import com.example.codingquiz.data.domain.QuizResults
import kotlinx.serialization.json.Json

class QuizResultNavType : NavType<QuizResults>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): QuizResults? =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            bundle.getParcelable(key, QuizResults::class.java)
        else bundle.getParcelable(key)

    override fun parseValue(value: String): QuizResults =
        Json.decodeFromString(value)

    override fun put(bundle: Bundle, key: String, value: QuizResults) =
        bundle.putParcelable(key, value)
}