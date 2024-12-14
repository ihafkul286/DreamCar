package com.example.dreamcar.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import com.example.dreamcar.activity.Car
import com.example.dreamcar.activity.Datasource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailHorizontalScreen() {
    val car: Car = Datasource.carList()[2]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Coche") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF607D8B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = "${car.brand} ${car.model}",
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(4f / 3f)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = car.description,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier.size(80.dp)
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.Favorite,
                            contentDescription = "Favorito",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}
