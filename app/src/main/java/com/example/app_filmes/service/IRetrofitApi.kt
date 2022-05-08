package com.example.app_filmes.service

import com.example.app_filmes.Constans.Companion.API_KEY
import com.example.app_filmes.model.MovieDetails
import com.example.app_filmes.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IRetrofitApi {
    @GET("trending/movie/week$API_KEY")
   fun getListFilmes(): Call<Response>

    @GET("movie/{movie_id}$API_KEY")
    fun detailFilmes(@Path("movie_id") id: Int): Call<MovieDetails>
}
