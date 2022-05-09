package com.example.app_filmes.repository

import com.example.app_filmes.view.model.MovieDetails
import com.example.app_filmes.view.model.Response
import retrofit2.Call

interface IMovieRepository {
    suspend fun getMovies(): Call<Response>
    suspend fun getDetailsMovies(movieId: Int): Call<MovieDetails>

}