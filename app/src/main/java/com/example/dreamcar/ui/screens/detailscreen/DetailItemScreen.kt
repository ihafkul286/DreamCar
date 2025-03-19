package com.example.dreamcar.ui.screens.detailscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.dreamcar.R
import com.example.dreamcar.ui.components.AsyncImageComp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItemScreen(
    personaje_id: Long,
    name: String,
    detailViewModel: DetailViewModel = viewModel(),
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val uiState by detailViewModel.uiState.collectAsState()
    detailViewModel.getPersonaje(personaje_id)

    if (uiState.isLoading) {
        CircularProgressIndicator()
    }

    if (uiState.userMessage != null){
        Text(uiState.userMessage!!.name)
        return
    }

    var commentText by remember { mutableStateOf("") }
    var commentsList by remember { mutableStateOf(listOf<Comment>()) }

    fun addComment() {
        if (commentText.isNotBlank()) {
            val newComment = Comment(author = name, text = commentText)
            commentsList = commentsList + newComment
            commentText = ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Personaje") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF607D8B),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar comentario")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImageComp(
                modifier = Modifier.fillMaxWidth(),
                contentDesc = "${uiState.personaje!!.name} - ${uiState.personaje!!.race}",
                imageLink = uiState.personaje!!.image,
            )

            Card(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        text = uiState.personaje!!.name,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        detailViewModel.changePersonajeFavoriteStatus()
                    },
                    modifier = Modifier.size(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.Favorite,
                        contentDescription = stringResource(R.string.more_content_desc),
                        tint = if (uiState.isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Comentarios", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(commentsList) { comment ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    text = comment.author,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(text = comment.text)
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    label = { Text("Escribe un comentario") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    maxLines = 3
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { addComment() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF607D8B))
                ) {
                    Text(text = "Agregar Comentario", color = Color.White)
                }
            }
        }
    }
}

data class Comment(
    val author: String,
    val text: String
)



