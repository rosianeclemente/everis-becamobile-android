package com.example.app_filmes.repository

import com.example.app_filmes.model.Movie
import com.example.app_filmes.model.Response
import com.example.app_filmes.service.IRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class MovieRepository(private val movieClient: IRetrofitApi): IMoviesRepository {
    override suspend fun getMovies(): Call<Response> {
        return withContext(Dispatchers.IO){
            movieClient.getListFilmes()
        }

    }
}