package com.example.movies.api

import com.example.movies.model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/filmes")
    fun listaCategoria(): Call<Categorias>
}