package com.example.dreamcar.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dreamcar.R
import com.example.dreamcar.activity.Car
import com.example.dreamcar.activity.Datasource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailItemScreen() {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = car.imageRes),
                contentDescription = "${car.brand} ${car.model}",
                modifier = Modifier
                    .fillMaxWidth()
            )

            Card(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                ){
                    Text(
                        text = car.description,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                        ,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = MaterialTheme.typography.bodyLarge.fontSize),

                        )
                }
            }

//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(color = Color.Gray),
//            ){
//                Text(
//                    text = car.description,
//                    color = Color.Black,
//                    modifier = Modifier
//                        .padding(horizontal = 16.dp, vertical = 8.dp)
//                    ,
//                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = MaterialTheme.typography.headlineSmall.fontSize),
//
//                )
//            }

//            Text(
//                text = car.description,
//                color = Color.Black,
//                modifier = Modifier
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .align(Alignment.Start),
//                style = MaterialTheme.typography.bodyLarge.copy(fontSize = MaterialTheme.typography.headlineSmall.fontSize)
//            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {  },
                    modifier = Modifier.size(100.dp)
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.Favorite,
                        contentDescription = stringResource(R.string.more_content_desc),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}
