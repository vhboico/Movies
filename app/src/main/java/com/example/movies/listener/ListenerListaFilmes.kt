package com.example.movies.listener

import com.example.movies.model.Categoria

interface ListenerListaFilmes {

    fun onResponse(listaCategoria: MutableList<Categoria>)
    fun onFailure(error: String)
}