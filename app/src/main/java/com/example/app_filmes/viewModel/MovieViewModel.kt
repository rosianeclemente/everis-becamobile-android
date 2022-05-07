package com.example.app_filmes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_filmes.model.Response
import com.example.app_filmes.repository.IMoviesRepository
import retrofit2.Call

class MovieViewModel(private val movieRepository: IMoviesRepository):ViewModel() {
    private val _movie = MutableLiveData<Call<Response>>()
    val movie: LiveData<Call<Response>> = _movie

    fun getMovieFromRetrofit(){

//        movieRepository.getMovies()
    }
}