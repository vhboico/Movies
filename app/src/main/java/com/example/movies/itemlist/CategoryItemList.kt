package com.example.movies.itemlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movies.model.Categoria
import com.example.movies.ui.theme.white

@Composable
fun CategoryItemList(
    position: Int,
    listaCategoria: MutableList<Categoria>
) {

    val title = listaCategoria[position].titulo
    val capa = listaCategoria[position].filmes

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = title.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )

        LazyRow{
            itemsIndexed(capa){ position, _ ->
                ImageItemList(position = position, pic = capa)
            }
        }
    }
}

