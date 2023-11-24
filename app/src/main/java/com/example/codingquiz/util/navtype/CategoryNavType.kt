package com.example.codingquiz.util.navtype

import android.os.Bundle
import androidx.navigation.NavType
import com.example.codingquiz.data.domain.Category
import kotlinx.serialization.json.Json

class CategoryNavType : NavType<Category>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Category? =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU)
            bundle.getParcelable(key, Category::class.java)
        else bundle.getParcelable(key)

    override fun parseValue(value: String): Category =
        Json.decodeFromString(value)

    override fun put(bundle: Bundle, key: String, value: Category) =
        bundle.putParcelable(key, value)
}