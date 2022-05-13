package com.example.app_filmes.viewModel

import androidx.lifecycle.*
import com.example.app_filmes.Data.IMovieRepository
import com.example.app_filmes.Domain.Model.MovieDetails
import com.example.app_filmes.Domain.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.await

class MovieDetailsViewModel(private val movieRepository: IMovieRepository) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun getMoviesDetailsFromRetrofit(movieId: Int) {

        viewModelScope.launch {
            val details = movieRepository.getDetailsMovies(movieId).await()
            _movieDetails.value = details
        }
    }
}
class MovieDetailsViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(repository) as T
    }
}
