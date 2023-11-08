package com.example.movies.itemlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.movies.model.Movies
import com.example.movies.ui.theme.Shapes
import com.example.movies.ui.theme.dark_gray

@Composable
fun ImageItemList(
    position: Int,
    pic: MutableList<Movies>,
    text: String
) {

    val picture = pic[position].pic

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
        Image(
            imageVector = ImageVector.vectorResource(id = picture!!.toInt()),
            contentDescription = "",
            modifier = Modifier
                .width(130.dp)
                .height(160.dp)
                .padding(start = 10.dp, end = 10.dp),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(dark_gray),
            contentAlignment = Alignment.Center
        ) {

            Text(text = text,
                modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}
