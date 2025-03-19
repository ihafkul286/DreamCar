package com.example.dreamcar.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dreamcar.R
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            "DreamCar",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFB0C4DE)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Content(Modifier.padding(innerPadding))
    }
}

private fun sendEmail(context: android.content.Context) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("ihafkul286@g.educaand.es"))
        putExtra(Intent.EXTRA_SUBJECT, "Información sobre la Aplicación DreamCar")
        putExtra(Intent.EXTRA_TEXT, """
            Hola soy (su nombre),
            
            Me interesaría obtener sobre el funcionandmiento de DreamCar.
            
            Gracias.
        """.trimIndent())
    }

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "No hay una aplicación de correo disponible", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(150.dp))

        Text(
            text = "Temática : Coches Deportivos",
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "En esta aplicación llena de coches espectaculares y de ensueño, " +
                    "podrás diseñar tu propio coche, además de agregarle mejoras o ajustes personalizados." +
                    "También incluye un cuestionario que te recomendará el coche ideal para ti.",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(85.dp))

        Text(
            text = "Versión 1.0.0",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(45.dp))

        val context = LocalContext.current

        Image(
            painter = painterResource(id = R.drawable.gmail),
            contentDescription = "Correo electrónico",
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { sendEmail(context) }
        )
    }
}

