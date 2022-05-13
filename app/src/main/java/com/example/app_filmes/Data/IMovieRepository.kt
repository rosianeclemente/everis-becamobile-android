package com.example.app_filmes.Data

import com.example.app_filmes.Domain.Model.MovieDetails
import com.example.app_filmes.Domain.Model.Response
import retrofit2.Call

interface IMovieRepository {
    suspend fun getMovies(): Call<Response>
    suspend fun getDetailsMovies(movieId: Int): Call<MovieDetails>
}
