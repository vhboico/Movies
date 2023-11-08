package com.example.movies.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.api.Api
import com.example.movies.itemlist.CategoryItemList
import com.example.movies.model.Categoria
import com.example.movies.model.Categorias
import com.example.movies.model.Filmes
import com.example.movies.ui.theme.black
import com.example.movies.ui.theme.dark_gray
import com.example.movies.ui.theme.red
import com.example.movies.ui.theme.white
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MoviesScreen(
    navController: NavController,
    listaCategorias: Categorias,
    categoria: MutableList<Categorias>,
    titulo: MutableList<Categoria>,
    capa: MutableList<Filmes>
) {

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

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://stackmobile.com.br/")
                .build()
                .create(Api::class.java)

            retrofit.listaCategoria().enqueue(object : Callback<Categorias> {
                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if (response.code() == 200) {
                        response.body().let {filmes ->
                            val x = listaCategorias.categorias.addAll(filmes!!.categorias)
                        }
                    }
                }
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                        Toast.makeText(context, "Erro inesperado", Toast.LENGTH_SHORT).show()
                }
            }
        )

            LazyColumn {
                itemsIndexed(categoria) { position, _ ->
                    CategoryItemList(
                        position = position,
                        titulo = titulo,
                        capa = capa
                    )
                }
            }
        }
    }
}