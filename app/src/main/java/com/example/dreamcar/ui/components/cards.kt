package com.example.dreamcar.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dreamcar.R
import com.example.dreamcar.model.Personaje

@Composable
fun CharacterCard(
    personaje: Personaje,
    onCardClick: () -> Unit,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                AsyncImageComp(
                    contentDesc = "",
                    contentScale = ContentScale.Crop,
                    imageLink = personaje.image,
                    placeholderDrawable = R.drawable.placeholder,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = 0.8f }
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
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
                            text = "${personaje.name} ${personaje.race}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        IconButton(
                            onClick = {onFavoriteClick()},
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.Favorite,
                                contentDescription = "Favorito",
                                tint = if (isFavorite) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CarCard(personaje: Personaje, isLandscape: Boolean, onCardClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }

    val cardModifier = if (isLandscape) {
        Modifier.fillMaxWidth(0.6f)
    } else {
        Modifier.fillMaxWidth()
    }

    Card(
        modifier = cardModifier
            .padding(8.dp)
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(200.dp)) {
                Image(
                    painter = painterResource(id = personaje.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = 0.8f }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth()
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
                            text = "${personaje.name} ${personaje.ki}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        IconButton(
                            onClick = { isFavorite = !isFavorite },
                            modifier = Modifier.size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.Favorite,
                                contentDescription = "Favorito",
                                tint = if (isFavorite) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}