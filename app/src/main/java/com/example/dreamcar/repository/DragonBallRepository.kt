package com.example.dreamcar.repository

import com.example.dreamcar.api.ApiService
import com.example.dreamcar.model.Personaje
import kotlin.random.Random


class DragonBallRepository(private val apiService: ApiService) {

    companion object {
        const val NUM_PERSONAJES = 58
    }

    suspend fun getPersonaje(id: Long): Result<Personaje>
    {
        return try {
            val response = apiService.getPersonaje(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it.toPersonaje())
                } ?: Result.failure(Exception("La respuesta del servidor está vacía"))
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun getRandomPersonaje(): Result<Personaje> {
        val seed = System.currentTimeMillis()
        val randomId = (1..NUM_PERSONAJES).random(Random(seed))
        return getPersonaje(randomId.toLong())
    }
    suspend fun getSomeRandomPersonaje(num: Int): Result<List<Personaje>> {
        val personajes = mutableListOf<Personaje>()
        for (i in 1..num) {
            val response = getRandomPersonaje()
            if (response.isFailure) return Result.failure(response.exceptionOrNull()!!)
            personajes.add(response.getOrNull()!!)
        }
        return Result.success(personajes)
    }
}