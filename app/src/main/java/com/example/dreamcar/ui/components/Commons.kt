package com.example.dreamcar.ui.components

import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.request.ImageRequest
import com.example.dreamcar.R

@Composable
fun AsyncImageComp(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    imageLink: String,
    placeholderDrawable: Int = R.drawable.placeholder,
    contentDesc: String = "",
    height: Int = 0,
    width: Int = 0
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .build(),
        contentDescription = contentDesc,
        contentScale = contentScale,
        modifier = modifier,
        placeholder = painterResource(placeholderDrawable),

        )
}


