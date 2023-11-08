package com.example.movies.repository

import com.example.movies.datasource.DataSourceApi
import com.example.movies.listener.ListenerListaFilmes
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RepositoryApi @Inject constructor(private val dataSourceApi: DataSourceApi) {

    fun getFilmes(listener: ListenerListaFilmes){
        return dataSourceApi.getFilmes(listener)
    }
}