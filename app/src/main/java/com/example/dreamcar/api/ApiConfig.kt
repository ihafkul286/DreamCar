package com.example.dreamcar.api


import com.example.dreamcar.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        private val contentType = "application/json".toMediaType()

        private val json = Json {
            ignoreUnknownKeys = true
        }

        fun provideRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(json.asConverterFactory(contentType))
                .baseUrl(baseUrl)
                .build()
        }
    }
}
