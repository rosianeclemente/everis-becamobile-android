package com.example.app_filmes.service

import com.example.app_filmes.Constans.Companion.API_KEY
import com.example.app_filmes.model.Filmes
import com.example.app_filmes.model.Result
import retrofit2.Call
import retrofit2.http.GET


interface IRetrofitApi {

    @GET("trending/movie/week$API_KEY")
     fun getListFilmes(
    ): Call<Filmes>


    @GET("movies/movie_id")
    fun detailFilmes(

    ): Call<Filmes>
}