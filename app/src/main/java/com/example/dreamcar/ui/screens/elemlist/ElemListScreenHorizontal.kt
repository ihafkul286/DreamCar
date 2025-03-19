package com.example.dreamcar.ui.screens.elemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dreamcar.model.Personaje


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreenHorizontal(
    modifier: Modifier = Modifier,
    elemViewModel: ElemListViewmodel = viewModel(),
    navController: NavController,
) {

    val uiState by elemViewModel.uiState.collectAsState()
    val characterList = uiState.personajes
    var searchText by remember { mutableStateOf("") }

    val filteredCharacterList = characterList.filter { personaje ->
        "${personaje.name}".contains(searchText, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(filteredCharacterList) { personaje ->
                CarCard(
                    personaje = personaje,
                    isLandscape = true,
                    onCardClick = { onElementClick("${personaje.name} ${personaje.ki}") }
                )
            }
        }
    }
}




