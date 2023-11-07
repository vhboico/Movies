package com.example.movies.repository

import com.example.movies.datasource.DataSource
import com.example.movies.listener.Listener
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(private val dataSource: DataSource) {

    fun signUp(name: String, email: String, password: String, listener: Listener){
        dataSource.signUp(name, email, password, listener)
    }
}