package com.example.movies.datasource

import com.example.movies.listener.Listener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class DataSource @Inject constructor() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun signUp(name: String, email: String, password: String, listener: Listener) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            listener.onFailure("Preencha todos os campos")
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
                        listener.onSuccess("Cadastro realizado com sucesso", "loginScreen")
                    }.addOnFailureListener {
                        listener.onFailure("Erro inesperado!")
                    }
                }
            }.addOnFailureListener { exception ->
                val erro = when (exception) {
                    is FirebaseAuthUserCollisionException -> "Essa conta já foi registrada"
                    is FirebaseAuthWeakPasswordException -> "A senha precisa conter pelo menos 6 caracteres"
                    is FirebaseNetworkException -> "Sem conexão com a internet"
                    else -> "Email inválido"
                }
                listener.onFailure(erro)
            }
        }
    }

    fun login(email: String, password: String, listener: Listener) {
        if (email.isEmpty() || password.isEmpty()) {
            listener.onFailure("Preencha todos os campos")
        } else {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    listener.onSuccess("Login realizado com sucesso", "moviesScreen")
                }
            }.addOnFailureListener { exception ->
                val erro = when (exception) {
                    is FirebaseAuthInvalidCredentialsException -> "Senha incorreta"
                    is FirebaseNetworkException -> "Sem conexão de internet"
                    else -> "Email inválido"
                }
                listener.onFailure(erro)
            }
        }
    }
}