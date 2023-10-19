package com.example.codingquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingquiz.data.domain.Category
import com.example.codingquiz.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    init {
        getCategories()
    }

    private val _categories = MutableStateFlow(emptyList<Category>())
    val categories get() = _categories.asStateFlow()

    fun getCategories() {
        viewModelScope.launch {
            _categories.value = categoryRepository.getAll()
        }
    }

    companion object {
        const val COLUMNS_NUM = 2
    }
}
