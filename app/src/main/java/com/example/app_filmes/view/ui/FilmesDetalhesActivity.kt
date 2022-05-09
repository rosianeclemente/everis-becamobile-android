package com.example.app_filmes.view.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app_filmes.databinding.ActivityFilmeDetalhesBinding
import com.example.app_filmes.view.model.Movie
import com.example.app_filmes.view.model.MovieDetails
import com.example.app_filmes.repository.MovieRepository
import com.example.app_filmes.service.RetrofitClient.Companion.MOVIE_SERVICE
import com.example.app_filmes.viewModel.MovieDetailsViewModel
import com.example.app_filmes.viewModel.MovieDetailsViewModelFactory

class FilmesDetalhesActivity() : AppCompatActivity() {

    private val binding by lazy {
        ActivityFilmeDetalhesBinding.inflate(layoutInflater)
    }
    private val movieRepository = MovieRepository(MOVIE_SERVICE)
    private val movieDetailsFactory = MovieDetailsViewModelFactory(movieRepository)
    private val movieDetailsViewModel by viewModels<MovieDetailsViewModel> { movieDetailsFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getMovie()

    }

    fun setDados(movieDetail: MovieDetails) {
        Glide.with(binding.root.context)
            .load(movieDetail.getImagemCapa())
            .centerCrop()
            .into(binding.filmeDetalhes)
        binding.nomeFilmeDetalhes.text = movieDetail.title
        binding.sinopeFilmeDetalhes.text = movieDetail.overview
        binding.avaliacaoFilmeDetalhes.rating = movieDetail.getAvaliacao()
    }

    //Feita sem MVVM e sem coroutines
    //    fun getMovie() {
//        val filme: Movie = intent.getSerializableExtra("filme") as Movie
//        val call: Call<MovieDetails> = MOVIE_SERVICE.detailFilmes(filme.movieId)
//        call.enqueue(object : Callback<MovieDetails> {
//            override fun onResponse(call: Call<MovieDetails>, response: retrofit2.Response<MovieDetails>,
//            ) {
//                val filmesDetalhes = response.body()
//                if (filmesDetalhes != null) {
//                    setDados(filmesDetalhes)
//                }
//            }
//
//            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
//                val filmesDetalhes = t.message
//            }
//        })
//    }
    fun getMovie() {
        val filme: Movie = intent.getSerializableExtra("filme") as Movie
        movieDetailsViewModel.getMoviesDetailsFromRetrofit(filme.id)
        movieDetailsViewModel.movieDetails.observe(this) { movieDetails ->
            setDados(movieDetails)
        }

    }

}