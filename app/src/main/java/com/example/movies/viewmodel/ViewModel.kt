package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.listener.Listener
import com.example.movies.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _name = MutableStateFlow("")
    private val name: StateFlow<String> = _name

    fun signUp(name: String, email: String, password: String, listener: Listener){
        viewModelScope.launch {
            repository.signUp(name, email, password, listener)
        }
    }

    fun login(email: String, password: String, listener: Listener){
        viewModelScope.launch {
            repository.login(email, password, listener)
        }
    }

    fun checkUser(): Flow<Boolean>{
        return repository.checkUser()
    }

    fun userProfile(): Flow<String>{
        viewModelScope.launch {
            repository.userProfile().collect{
                _name.value = it
            }
        }
        return name
    }
}