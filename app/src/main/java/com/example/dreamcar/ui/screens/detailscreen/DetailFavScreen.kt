package com.example.dreamcar.ui.screens.detailscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.dreamcar.ui.components.AsyncImageComp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFavScreen(personaje_id: Long,
                    name: String,
                    detailViewModel: DetailViewModel = viewModel(),
                    navController: NavController,
                    backStackEntry: NavBackStackEntry
) {
    val uiState by detailViewModel.uiState.collectAsState()
    detailViewModel.getPersonaje(personaje_id)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.personaje!!.name) },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF607D8B),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
                containerColor = Color(0xFF607D8B),
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir Comentario")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AsyncImageComp(
                modifier = Modifier.fillMaxWidth(),
                contentDesc = "${uiState.personaje!!.name} - ${uiState.personaje!!.race}",
                imageLink = uiState.personaje!!.image,
            )
            Spacer(
                Modifier.height(50.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = "${uiState.personaje!!.name} ${uiState.personaje!!.race}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = uiState.personaje!!.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ki: ${uiState.personaje!!.ki}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )

                }
            }

            Text(
                text = "Comentarios:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = car.comments,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }
    }
}
