package com.example.dreamcar.applicationrelease

import android.app.Application
import com.example.dreamcar.BuildConfig
import com.example.dreamcar.api.ApiConfig
import com.example.dreamcar.repository.DragonBallRepository
import com.example.dreamcar.api.ApiService
import com.example.dreamcar.repository.PersonajesRepository
import com.example.dreamcar.localdatabase.DragonDatabase

class DragonBallReleaseApplication : Application() {
    private val dragonBallApiService: ApiService by lazy {
        ApiConfig
            .provideRetrofit(BuildConfig.BASE_URL)
            .create(ApiService::class.java)
    }

    val dragonRepository: DragonBallRepository by lazy { DragonBallRepository(dragonBallApiService) }

    val personajeRepository: PersonajesRepository by lazy { PersonajesRepository(DragonDatabase.getDatabase(this).personajeDao()) }
}
