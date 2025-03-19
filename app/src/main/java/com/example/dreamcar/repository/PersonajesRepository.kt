package com.example.dreamcar.repository

import com.example.dreamcar.localdatabase.PersonajeDAO
import com.example.dreamcar.model.Personaje
import kotlinx.coroutines.flow.Flow

class PersonajesRepository(private val personajeDao: PersonajeDAO): PersonajeInterfaz {

    override suspend fun insertCharacter(personaje: Personaje): Long = personajeDao.insert(personaje)

    override suspend fun deleteCharacter(personaje: Personaje) = personajeDao.delete(personaje)

    override suspend fun update(personaje: Personaje) = personajeDao.update(personaje)

    override val getAllCharacters: Flow<List<Personaje>> = personajeDao.getAll()

    override suspend fun getCharacter(id: Long): Personaje = personajeDao.getPersonaje(id)
}