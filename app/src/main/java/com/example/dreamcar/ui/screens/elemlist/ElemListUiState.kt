package com.example.dreamcar.ui.screens.elemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dreamcar.applicationrelease.DragonBallReleaseApplication
import com.example.dreamcar.model.Personaje
import kotlinx.coroutines.flow.StateFlow

data class ElemListUiState (
    val personajes : List<Personaje> = emptyList(),
    val personajesFav : List<Personaje> = emptyList(),
    val isLoading: Boolean = true,
    val userMessage: UserMessages? = null

)
enum class UserMessages {
    ERROR_LOADING_CHARACTERS
}