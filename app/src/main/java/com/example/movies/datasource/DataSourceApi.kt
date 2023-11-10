package com.example.movies.datasource

import com.example.movies.api.Api
import com.example.movies.listener.ListenerListaFilmes
import com.example.movies.model.Categorias
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DataSourceApi @Inject constructor() {
    fun getFilmes(listener: ListenerListaFilmes) {

        val retrofit: Api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(Api::class.java)

        retrofit.listaCategoria().enqueue(object : Callback<Categorias> {
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if (response.isSuccessful) {
                    listener.onResponse(response.body()!!.categorias)
                }
            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {
                listener.onFailure("Server error!")
            }

        }
        )
    }
}

