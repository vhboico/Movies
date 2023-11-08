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
import com.example.movies.model.Category
import com.example.movies.model.Movies
import com.example.movies.ui.theme.white

@Composable
fun CategoryItemList(
    position: Int,
    item: MutableList<Category>,
    movies: MutableList<Movies>
) {

    val title = item[position].title

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
            itemsIndexed(movies){position,_ ->
                ImageItemList(position = position, pic = movies, text = "")
            }
        }
    }
}

