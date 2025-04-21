package com.example.mediaexplorer

data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val contents: List<MediaContent> = emptyList()
)


data class MediaContent(
    val id: Int,
    val title: String,
    val description: String = "",
    val ranking: Int = 0
)