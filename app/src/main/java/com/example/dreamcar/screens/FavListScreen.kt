package com.example.dreamcar.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dreamcar.R
import com.example.dreamcar.activity.Datasource


@OptIn(ExperimentalMaterial3Api::class) // Anotación para usar funciones experimentales
@Composable
fun FavListScreen() {
    val favoriteCars = Datasource.carList()

    Scaffold(
        topBar = {
            // Usando el TopAppBar con colores experimentales
            TopAppBar(
                title = { Text("Favourite Cars") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF607D8B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(favoriteCars) { car ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = car.imageRes),
                            contentDescription = "${car.brand} ${car.model}",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${car.brand} ${car.model}",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                )
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.TwoTone.Delete,
                                        contentDescription = "Remove Favorite",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





