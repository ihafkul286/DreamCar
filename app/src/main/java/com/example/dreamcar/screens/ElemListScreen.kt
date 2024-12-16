package com.example.dreamcar.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamcar.activity.Car
import com.example.dreamcar.activity.Datasource
import com.example.ui.theme.bodyFontFamily
import com.example.ui.theme.displayFontFamily
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen(
    onElementClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    onProfileClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    val typography = Typography(
        bodyLarge = TextStyle(
            fontFamily = bodyFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = displayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        ),
    )

    MaterialTheme(typography = typography) {

        val carList = Datasource.carList()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lista de Coches Deportivos") },
                    actions = {
                        IconButton(onClick = onFavoritesClick) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favoritos"
                            )
                        }
                        IconButton(onClick = onProfileClick) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Perfil"
                            )
                        }
                        IconButton(onClick = onAboutClick) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "Acerca de"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(carList) { car ->
                    CarCard(car = car, onClick = { onElementClick(car.model) })
                }
            }
        }
    }
}

@Composable
fun CarCard(car: Car, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painterResource(id = car.imageRes),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = 0.8f }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${car.brand} ${car.model}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        IconButton(
                            onClick = {
                                var isFavorite = !isFavorite
                            },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorito",
                                tint = if (isFavorite) Color.Red else Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}





