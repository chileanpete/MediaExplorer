package com.example.mediaexplorer.datos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mediaexplorer.Category
import com.example.mediaexplorer.MediaContent
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategoria(categoria: Category)

    @Update
    suspend fun updateCategoria(categoria: Category)

    @Delete
    suspend fun deleteCategoria(categoria: Category)

    @Query("SELECT * from categories WHERE idcat = :id")
    fun getCategoria(id: Int): Flow<Category>

    @Query("SELECT * from categories ORDER BY name ASC")
    fun getAllCategoria(): Flow<List<Category>>
}

@Dao
interface ContentDao{
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContenido(contenido: MediaContent)

    @Update
    suspend fun updateContenido(contenido: MediaContent)

    @Delete
    suspend fun deleteContenido(contenido: MediaContent)

    @Query("SELECT * from contents WHERE idcont = :id")
    fun getContenido(id: Int): Flow<MediaContent>

    @Query("SELECT * from contents ORDER BY title ASC")
    fun getAllContenido(): Flow<List<MediaContent>>
}