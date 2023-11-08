package com.example.movies.model

import com.google.gson.annotations.SerializedName

data class Categoria(
    @SerializedName("titulo") val titulo: String? = null,
    @SerializedName("capas") val filmes: MutableList<Filmes> = mutableListOf()
)

data class Filmes(
    @SerializedName("url_imagem") val capa: String? = null,
    @SerializedName("id") val id: Int = 0
)

data class Categorias(@SerializedName("categoria")
    val categorias: MutableList<Categoria> = mutableListOf()
)