package com.example.dreamcar.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dreamcar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF607D8B),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            IconButton(
                onClick = {  },
                modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    modifier = Modifier.size(48.dp),
                    contentDescription = "Profile",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

            Text(
                text = "Lamine Yamal",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "lamineyamal@gmail.com",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = {  },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF607D8B),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Login / Logout")
            }
        }
    }
}
