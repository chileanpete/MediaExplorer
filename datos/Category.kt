package com.example.mediaexplorer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val idcat: Int,
    val name: String,
    val description: String,
    val contents: List<MediaContent> = emptyList()
)

@Entity(tableName = "contents")
data class MediaContent(
    @PrimaryKey(autoGenerate = true)
    val idcont: Int,
    val title: String,
    val description: String = "",
    val ranking: Int = 0
)