package com.example.mediaexplorer

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MediaViewModel : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private var nextCategoryId = 1
    private var nextContentId = 1

    fun addCategory(name: String, description: String) {
        val newCategory = Category(
            id = nextCategoryId++,
            name = name,
            description = description,
            contents = emptyList()
        )
        _categories.update { currentList -> currentList + newCategory }
    }

    fun addContent(categoryId: Int, content: MediaContent) {
        _categories.update { currentList ->
            currentList.map { category ->
                if (category.id == categoryId) {
                    category.copy(contents = category.contents + content.copy(id = nextContentId++))
                } else {
                    category
                }
            }
        }
    }

    fun getCategoryById(id: Int): Category? {
        return _categories.value.find { it.id == id }
    }

    fun getContentById(categoryId: Int, contentId: Int): MediaContent? {
        return _categories.value.find { it.id == categoryId }
            ?.contents?.find { it.id == contentId }
    }
}