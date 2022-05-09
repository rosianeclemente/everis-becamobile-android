package com.example.app_filmes.repository

import com.example.app_filmes.view.model.MovieDetails
import com.example.app_filmes.view.model.Response
import com.example.app_filmes.service.IRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepository(private val movieClient: IRetrofitClient): IMovieRepository {
    override suspend fun getMovies(): Call<Response> {
        return withContext(Dispatchers.IO){
            movieClient.getListFilmes()
        }

    }

    override suspend fun getDetailsMovies(movieID: Int): Call<MovieDetails> {
        return withContext(Dispatchers.IO){
            movieClient.detailFilmes(movieId= movieID)
        }
    }
}