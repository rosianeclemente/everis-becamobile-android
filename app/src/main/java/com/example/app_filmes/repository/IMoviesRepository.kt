package com.example.app_filmes.repository

import com.example.app_filmes.model.Movie
import com.example.app_filmes.model.Response
import retrofit2.Call

interface IMoviesRepository {
    suspend fun  getMovies(): Call<Response>

}