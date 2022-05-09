package com.example.app_filmes.service

import com.example.app_filmes.Constans.Companion.API_KEY
import com.example.app_filmes.view.model.MovieDetails
import com.example.app_filmes.view.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IRetrofitClient {
    @GET("trending/movie/week$API_KEY")
   fun getListFilmes(): Call<Response>

    @GET("movie/{movie_id}$API_KEY")
    fun detailFilmes(@Path("movie_id") movieId: Int): Call<MovieDetails>
}
