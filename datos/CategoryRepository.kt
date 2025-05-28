package com.example.mediaexplorer.datos

import com.example.mediaexplorer.Category
import com.example.mediaexplorer.MediaContent
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    fun getAllCategoriaStream(): Flow<List<Category>>

    fun getCategoriaStream(id: Int): Flow<Category?>

    suspend fun insertCategoria(categoria: Category)

    suspend fun deleteCategoria(categoria: Category)

    suspend fun updateCategoria(categoria: Category)
}

interface ContentRepository {

    fun getAllContenidoStream(): Flow<List<MediaContent>>

    fun getContenidoStream(id: Int): Flow<MediaContent?>

    suspend fun insertContenido(content: MediaContent)

    suspend fun deleteContenido(content: MediaContent)

    suspend fun updateContenido(content: MediaContent)
}

