package com.example.dreamcar.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.dreamcar.data.UserPreferences
import com.example.dreamcar.data.UserPreferencesRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userPreferencesRepository: UserPreferencesRepository
) {

    val coroutineScope = rememberCoroutineScope()

    var username by remember { mutableStateOf("Usuario") }
    var darkMode by remember { mutableStateOf("system") }
    var isEditing by remember { mutableStateOf(false) }
    var newUsername by remember { mutableStateOf(TextFieldValue(username)) }

    LaunchedEffect(Unit) {
        userPreferencesRepository.userPrefs.collect { prefs ->
            username = prefs.username
            darkMode = prefs.darkMode
            newUsername = TextFieldValue(prefs.username)
        }
    }

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
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )

            if (isEditing) {
                TextField(
                    value = newUsername,
                    onValueChange = { newUsername = it },
                    label = { Text("Enter new username") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                userPreferencesRepository.savePreferences(
                                    UserPreferences(newUsername.text, darkMode)
                                )
                                username = newUsername.text
                                isEditing = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF607D8B),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Save")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { isEditing = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Cancel")
                    }
                }
            } else {
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = { isEditing = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF607D8B),
                        contentColor = Color.White
                    )
                ) {
                    Text("Edit Username")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Dark Mode: ${darkMode.replaceFirstChar { it.uppercase() }}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = darkMode == "dark",
                    onCheckedChange = { isChecked ->
                        val newMode = if (isChecked) "dark" else "light"
                        darkMode = newMode
                        coroutineScope.launch {
                            userPreferencesRepository.savePreferences(UserPreferences(username, newMode))
                        }
                    }
                )
                Text(if (darkMode == "dark") "Enabled" else "Disabled")
            }
        }
    }
}
