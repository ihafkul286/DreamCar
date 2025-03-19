package com.example.dreamcar.repository

import com.example.dreamcar.model.Personaje
import kotlinx.coroutines.flow.Flow

interface PersonajeInterfaz {
    suspend fun insertCharacter(personaje: Personaje): Long

    suspend fun deleteCharacter(personaje: Personaje)

    suspend fun update(personaje: Personaje)

    val getAllCharacters: Flow<List<Personaje>>

    suspend fun getCharacter(id: Long): Personaje
}