package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.listener.ListenerListaFilmes
import com.example.movies.repository.RepositoryApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelApi @Inject constructor(private val repositoryApi: RepositoryApi): ViewModel() {

    fun getFilmes(listener: ListenerListaFilmes){
        repositoryApi.getFilmes(listener)
    }
}