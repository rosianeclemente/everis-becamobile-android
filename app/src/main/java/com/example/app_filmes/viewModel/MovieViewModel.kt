package com.example.app_filmes.viewModel

import androidx.lifecycle.*
import com.example.app_filmes.view.model.Movie
import com.example.app_filmes.view.model.MovieApiResult
import com.example.app_filmes.repository.IMovieRepository
import com.example.app_filmes.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.await

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {
    private val _movie = MutableLiveData<MovieApiResult<List<Movie>>>()
    val movie: LiveData<MovieApiResult<List<Movie>>> = _movie

    fun getMovieFromRetrofit() {
        viewModelScope.launch {
            _movie.value = MovieApiResult.Loading()
            try {
                val movieApi = movieRepository.getMovies().await()
                _movie.value = MovieApiResult.Success(movieApi.results)
            } catch (e: Exception) {
               val movieResult = MovieApiResult.Error<List<Movie>>(e)
                _movie.value = movieResult
            }
        }
    }

}
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(repository) as T
    }

}