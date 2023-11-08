package com.example.movies.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.itemlist.CategoryItemList
import com.example.movies.model.Category
import com.example.movies.model.Movies
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.dark_gray
import com.example.movies.ui.theme.red
import com.example.movies.ui.theme.white
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(navController: NavController) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Movies",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = red
                    )
                },
                backgroundColor = black,
                actions = {

                    Text(
                        text = "Series",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
                    )

                    Text(
                        text = "Movies",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = "My list",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                    Text(
                        text = "John",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                        modifier = Modifier
                    )

                    TextButton(onClick = {
                        val alertDialog = AlertDialog.Builder(context)
                        alertDialog.setTitle("Do you really want to leave?")
                        alertDialog.setMessage("If you log out, you will have to log in again")
                        alertDialog.setPositiveButton("Yes") { _, _ ->
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate("loginScreen")

                        }
                        alertDialog.setNegativeButton("No") { _, _ -> }
                            .show()
                    }
                    ) {
                        Text(
                            text = "Out",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = white
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(dark_gray)
        ) {

            val item: MutableList<Category> = mutableListOf(
                Category("Filme 1"),
                Category("Filme 2"),
                Category("Filme 3"),
                Category("Filme 4"),
                Category("Filme 5"),
                Category("Filme 6"),
            )

            val movies: MutableList<Movies> = mutableListOf(
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
                Movies(R.drawable.ic_launcher_background),
            )

            LazyColumn {
                itemsIndexed(item) { position, _ ->
                    CategoryItemList(position = position, item = item, movies = movies)
                }
            }
        }
    }
}