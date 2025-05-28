package com.example.mediaexplorer.datos

import com.example.mediaexplorer.Category
import com.example.mediaexplorer.MediaContent
import kotlinx.coroutines.flow.Flow

class LocalCategoryRepository(private val itemcatDao: CategoryDao): CategoryRepository {
    override fun getAllCategoriaStream(): Flow<List<Category>> = itemcatDao.getAllCategoria()

    override fun getCategoriaStream(id: Int): Flow<Category?> = itemcatDao.getCategoria(id)

    override suspend fun insertCategoria(categoria: Category) = itemcatDao.insertCategoria(categoria)

    override suspend fun deleteCategoria(categoria: Category) = itemcatDao.deleteCategoria(categoria)

    override suspend fun updateCategoria(categoria: Category) = itemcatDao.updateCategoria(categoria)

}

class LocalContentRepository(private val itemcontDao: ContentDao): ContentRepository {
    override fun getAllContenidoStream(): Flow<List<MediaContent>> = itemcontDao.getAllContenido()

    override fun getContenidoStream(id: Int): Flow<MediaContent?> = itemcontDao.getContenido(id)

    override suspend fun insertContenido(contenido: MediaContent) = itemcontDao.insertContenido(contenido)

    override suspend fun deleteContenido(contenido: MediaContent) = itemcontDao.deleteContenido(contenido)

    override suspend fun updateContenido(contenido: MediaContent) = itemcontDao.updateContenido(contenido)

}