package com.example.dreamcar.api

import com.example.dreamcar.datamodel.FullPersonaje
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("characters/{id}")
    suspend fun getPersonaje(
        @Path("id") id: Long
    ): Response <FullPersonaje>
}
