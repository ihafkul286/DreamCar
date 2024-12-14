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
import androidx.compose.material3.windowsizeclass.WindowSizeClass
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElemListScreen() {
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

    MaterialTheme(
        typography = typography
    ) {
        val carList = Datasource.carList()

        Scaffold(
            topBar = { TopAppBar(title = { Text("Lista de Coches Deportivos") }) }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(carList) { car ->
                    CarCard(car)
                }
            }
        }
    }
}

@Composable
fun CarCard(car: Car) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                }
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
                            onClick = { },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.Favorite,
                                contentDescription = "Favorito",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}





