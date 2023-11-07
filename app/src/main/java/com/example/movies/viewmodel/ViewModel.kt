package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.listener.Listener
import com.example.movies.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun signUp(name: String, email: String, password: String, listener: Listener){
        repository.signUp(name, email, password, listener)
    }

    fun login(email: String, password: String, listener: Listener){
        repository.login(email, password, listener)
    }
}