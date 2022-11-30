package com.example.composepoc

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MoviePicture(movie: Movie, moviePicSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray //MaterialTheme.colors.lightGreen
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.thumbnail),
            contentDescription = "",
            modifier = Modifier.size(moviePicSize),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun MovieContent(movie: Movie, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment,
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides ContentAlpha.medium
        ) {
            Text(text = movie.name, style = MaterialTheme.typography.h6)
        }
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = movie.desc,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
fun MoviePictureDetails(movie: Movie, moviePicSize: Dp) {
    Card(
        shape = CutCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray //MaterialTheme.colors.lightGreen
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberAsyncImagePainter(movie.thumbnail),
            contentDescription = "",
            modifier = Modifier.size(moviePicSize),
            contentScale = ContentScale.Crop
        )
    }
}

