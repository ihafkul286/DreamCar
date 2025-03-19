package com.example.dreamcar.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dreamcar.model.Personaje
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonajeDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(word: Personaje): Long

    @Delete
    suspend fun delete(word: Personaje)

    @Update
    suspend fun update(word: Personaje)

    @Query("SELECT * FROM personajes")
    fun getAll(): Flow<List<Personaje>>

    @Query("SELECT * FROM personajes WHERE id = :id")
    fun getPersonaje(id: Long): Personaje
}
