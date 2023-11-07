package com.example.movies.datasource

import com.example.movies.listener.Listener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val _checkUserLogged = MutableStateFlow(false)
    private val checkUserLogged: StateFlow<Boolean> = _checkUserLogged

    fun signUp(name: String, email: String, password: String, listener: Listener) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            listener.onFailure("Complete all fields")
        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { signUp ->
                if (signUp.isSuccessful) {
                    val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

                    val map = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "userID" to userID
                    )

                    db.collection("users").document(userID).set(map).addOnCompleteListener {
                        listener.onSuccess("Successful registration", "loginScreen")
                    }.addOnFailureListener {
                        listener.onFailure("Unexpected error")
                    }
                }
            }.addOnFailureListener { exception ->
                val error = when (exception) {
                    is FirebaseAuthUserCollisionException -> "This account has already been registered"
                    is FirebaseAuthWeakPasswordException -> "The password must contain at least 6 characters"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listener.onFailure(error)
            }
        }
    }
    fun login(email: String, password: String, listener: Listener) {
        if (email.isEmpty() || password.isEmpty()) {
            listener.onFailure("Complete all fields")
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onSuccess("Login completed", "moviesScreen")
                }
            }.addOnFailureListener { exception ->
                val error = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Incorrect password"
                    is FirebaseNetworkException -> "No internet connection"
                    else -> "Invalid email"
                }
                listener.onFailure(error)
            }
        }
    }

    fun checkUser(): Flow<Boolean>{
        val userChecked = FirebaseAuth.getInstance().currentUser

        _checkUserLogged.value = userChecked != null
        return checkUserLogged
    }
}