package com.example.app_filmes.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.app_filmes.view.adapter.ResultAdapter
import com.example.app_filmes.databinding.ActivityMainBinding
import com.example.app_filmes.view.model.Movie
import com.example.app_filmes.view.model.MovieApiResult
import com.example.app_filmes.repository.MovieRepository
import com.example.app_filmes.service.RetrofitClient.Companion.MOVIE_SERVICE
import com.example.app_filmes.viewModel.MovieViewModel
import com.example.app_filmes.viewModel.MovieViewModelFactory


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val filmesAdapter by lazy {
        ResultAdapter(onClickListener = { filme ->
            filmesDetalhes(filme)
        })
    }
    private val movieRepository = MovieRepository(MOVIE_SERVICE)
    private val movieFactory = MovieViewModelFactory(movieRepository)
    private val movieViewModel by viewModels<MovieViewModel>{movieFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.filmesRecyclerView.adapter = filmesAdapter
//        getMovieFromApi()

        movieViewModel.getMovieFromRetrofit()
        movieAndObserve()
    }
    private fun movieAndObserve(){
        movieViewModel.getMovieFromRetrofit()
        movieViewModel.movie.observe(this){ movieApiResult->
            when(movieApiResult){
                is MovieApiResult.Loading -> {
                    Log.d("INFO", "Loanding")
                }
                is MovieApiResult.Success -> {
                    setListFilmes(movieApiResult.data)
                }
                is MovieApiResult.Error -> {
                    Log.d("INFO", "Error:${movieApiResult.throwable.cause}")
                }
            }
        }
    }

//    private fun getMovieFromApi(){
//        val call: Call<Response> = MOVIE_SERVICE.getListFilmes()
//        call.enqueue(object : Callback<Response> {
//
//            override fun onFailure(call: Call<Response>, t: Throwable) {
//                val filme = t.message
//            }
//            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//                val filme= response.body()
//
//                getFilmes(filme)
//            }
//        })
//    }

//    private fun getFilmes(filme: Response?) {
//        MOVIE_SERVICE.getListFilmes()
//        filme?.results?.let {
//            setListFilmes(it)
//        }
//    }
    private fun filmesDetalhes(filme: Movie) {
        val intent = Intent(this, FilmesDetalhesActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)

    }
    private fun setListFilmes(list: List<Movie>) {
        filmesAdapter.submitList(list)
    }
}
