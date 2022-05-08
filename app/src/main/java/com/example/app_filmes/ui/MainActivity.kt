package com.example.app_filmes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_filmes.adapter.ResultAdapt
import com.example.app_filmes.databinding.ActivityMainBinding
import com.example.app_filmes.model.Movie
import com.example.app_filmes.model.Response
import com.example.app_filmes.service.RetrofitCliente.Companion.movieService
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val filmesAdapter by lazy {
        ResultAdapt(onClickListener = { filme ->
            filmesDetalhes(filme)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.filmesRecyclerView.adapter = filmesAdapter
        getMovieFromApi()
    }

    private fun getMovieFromApi(){
        val call: Call<Response> = movieService.getListFilmes()
        call.enqueue(object : Callback<Response> {

            override fun onFailure(call: Call<Response>, t: Throwable) {
                val filme = t.message
            }
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val filme= response.body()

                getFilmes(filme)
            }
        })
    }

    private fun getFilmes(filme: Response?) {
        movieService.getListFilmes()
        filme?.results?.let {
            setListFilmes(it)
        }
    }
    private fun filmesDetalhes(filme: Movie) {
        val intent = Intent(this, FilmesDetalhesActivity::class.java)
        intent.putExtra("filme", filme)
        startActivity(intent)

    }
    private fun setListFilmes(list: List<Movie>) {
        filmesAdapter.submitList(list)
    }
}
