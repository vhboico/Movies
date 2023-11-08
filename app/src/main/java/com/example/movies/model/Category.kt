package com.example.movies.model

data class Category(
    val title: String? = null,
    val movie: MutableList<Movies> = mutableListOf()
)

data class Movies(
    val pic: Int? = null
)