package com.example.dreamcar.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dreamcar.activity.Car
import com.example.dreamcar.activity.Datasource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailFavScreen(name: String) {
    val car: Car = Datasource.carList()[0]
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${car.brand} ${car.model}") },
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
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = "${car.brand} ${car.model}",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop


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
                        text = "${car.brand} ${car.model}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = car.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Año: ${car.years}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                    Text(
                        text = "Potencia: ${car.horses} HP",
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
