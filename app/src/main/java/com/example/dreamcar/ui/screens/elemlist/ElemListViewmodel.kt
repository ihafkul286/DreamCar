package com.example.dreamcar.ui.screens.elemlist

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dreamcar.applicationrelease.DragonBallReleaseApplication
import com.example.dreamcar.model.Personaje
import com.example.dreamcar.repository.DragonBallRepository
import com.example.dreamcar.repository.PersonajesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ElemListViewmodel(
    private val dragonRepository: DragonBallRepository,
    private val personajesRepository: PersonajesRepository
) : ViewModel() {
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DragonBallReleaseApplication)
                ElemListViewmodel(application.dragonRepository, application.personajeRepository)
            }
        }
    }

    private val _uiState = MutableStateFlow(
        ElemListUiState()
    )
    val uiState: StateFlow<ElemListUiState> = _uiState

    init {
        loadCharactersFromApi(10)
        viewModelScope.launch {
            personajesRepository.getAllCharacters
                .catch { _ ->
                    _uiState.update {
                        it.copy(
                            userMessage = UserMessages.ERROR_LOADING_CHARACTERS
                        )
                    }

                }
                .collectLatest {
                    _uiState.update { currentState ->
                        currentState.copy(
                            personajesFav = it.toMutableList()
                        )
                    }
                }
        }
    }

    private fun loadCharactersFromApi(i: Int) {
        viewModelScope.launch {
            val personajesRes = dragonRepository.getSomeRandomPersonaje(i)
            if (personajesRes.isSuccess) {
                _uiState.update { currentState ->
                    val personajes = currentState.personajes.toMutableList()
                    personajes.addAll(personajesRes.getOrNull()?.toMutableList() ?: mutableListOf())
                    personajes.sortBy { it.name }
                    currentState.copy(
                        personajes = personajes,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        userMessage = UserMessages.ERROR_LOADING_CHARACTERS
                    )
                }
            }
        }
    }

    fun onHeroDeleted(heroNameSelected: String) {
        _uiState.update { currentState ->
            currentState.copy(
                personajes = currentState.personajes.filter { it.name != heroNameSelected }
                    .toMutableList()
            )
        }
    }

    fun onFavoriteClick(personaje: Personaje) {

        viewModelScope.launch {
            if (_uiState.value.personajesFav.contains(personaje)) {
                personajesRepository.deleteCharacter(personaje)
            } else {
                personajesRepository.insertCharacter(personaje)
            }
        }

    }

    val recruitHeroes: (Int) -> Unit = { i ->
        loadCharactersFromApi(i)
    }
}
