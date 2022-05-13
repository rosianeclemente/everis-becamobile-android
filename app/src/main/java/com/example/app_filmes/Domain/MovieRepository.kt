package com.example.app_filmes.Domain

import com.example.app_filmes.Data.IMovieRepository
import com.example.app_filmes.Data.IRetrofitClient
import com.example.app_filmes.Domain.Model.MovieDetails
import com.example.app_filmes.Domain.Model.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepository(private val movieClient: IRetrofitClient) : IMovieRepository {
    override suspend fun getMovies(): Call<Response> {
        return withContext(Dispatchers.IO) {
            movieClient.getListFilmes()
        }
    }

    override suspend fun getDetailsMovies(movieID: Int): Call<MovieDetails> {
        return withContext(Dispatchers.IO) {
            movieClient.detailFilmes(movieId = movieID)
        }
    }
}
