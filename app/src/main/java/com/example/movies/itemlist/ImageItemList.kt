package com.example.movies.itemlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movies.model.Filmes
import com.example.movies.ui.theme.Shapes
import com.example.movies.ui.theme.dark_gray
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ImageItemList(
    position: Int,
    pic: MutableList<Filmes>,
    navController: NavController
) {

    val picture = pic[position].capa
    val txt = pic[position].nome
    val desc = pic[position].descricao
    val cast = pic[position].elenco

    val encodeUrl = URLEncoder.encode(picture, StandardCharsets.UTF_8.toString())

    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.surface.copy(alpha = 0.12f),
                shape = Shapes.small
            )
            .width(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = {
                navController.navigate("descriptionScreen/${encodeUrl}/${txt}/${desc}/${cast}")
            }) {
            AsyncImage(
                model = picture,
                contentDescription = "",
                modifier = Modifier
                    .width(130.dp)
                    .height(160.dp)
                    .padding(start = 10.dp, end = 10.dp),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(dark_gray),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}
