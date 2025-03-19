package com.example.dreamcar.ui.screens.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dreamcar.applicationrelease.DragonBallReleaseApplication
import com.example.dreamcar.repository.DragonBallRepository
import com.example.dreamcar.repository.PersonajesRepository
import com.example.dreamcar.ui.screens.elemlist.UserMessages
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val dragonBallRepository: DragonBallRepository,
    private val personajeRepository: PersonajesRepository
) : ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DragonBallReleaseApplication)
                DetailViewModel(application.dragonRepository, application.personajeRepository)
            }
        }
    }

    private val _uiState = MutableStateFlow(
        DetailUiState()
    )
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun getPersonaje(id: Long) {
        viewModelScope.launch {
            val personajeRes = dragonBallRepository.getPersonaje(id)
            val personajeFav = personajeRepository.getCharacter(id)

            if (personajeRes.isSuccess) {
                val personaje = personajeRes.getOrNull()

                personaje?.let {
                    _uiState.update { currentState ->
                        currentState.copy(
                            personaje = it,
                            isLoading = false,
                            isFavorite = it.id == personajeFav.id,
                        )
                    }
                }

            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        personaje = null,
                        isLoading = false,
                        userMessage = UserMessages.ERROR_LOADING_CHARACTERS
                    )
                }
            }


        }
    }

    fun changePersonajeFavoriteStatus() {
        TODO("Not yet implemented")
    }
}


