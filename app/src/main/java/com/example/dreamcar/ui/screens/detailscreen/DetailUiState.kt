package com.example.dreamcar.ui.screens.detailscreen

import com.example.dreamcar.model.Personaje
import com.example.dreamcar.ui.screens.elemlist.UserMessages

data class DetailUiState (
    val personaje: Personaje? = null,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val userMessage: UserMessages? = null
)




