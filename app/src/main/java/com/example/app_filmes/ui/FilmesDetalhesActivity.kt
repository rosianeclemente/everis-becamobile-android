package com.example.app_filmes.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.app_filmes.Constans
import com.example.app_filmes.databinding.ActivityFilmeDetalhesBinding
import com.example.app_filmes.model.Movie
import com.example.app_filmes.model.MovieDetails
import com.example.app_filmes.model.Response
import com.example.app_filmes.service.IRetrofitApi
import com.example.app_filmes.service.RetrofitCliente
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmesDetalhesActivity() : AppCompatActivity() {

    private val binding by lazy {
        ActivityFilmeDetalhesBinding.inflate(layoutInflater)
    }
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

    fun getMovie() {
        val filme: Movie = intent.getSerializableExtra("filme") as Movie
        val remote = RetrofitCliente.createService(IRetrofitApi::class.java)
        val call: Call<MovieDetails> = remote.detailFilmes(filme.id)
        call.enqueue(object : Callback<MovieDetails>{
            override fun onResponse(
                call: Call<MovieDetails>,
                response: retrofit2.Response<MovieDetails>,
            ) {
                val s = response.body()
                if (s != null) {
                    setDados(s)
                }
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                val s = t.message
            }
        })
    }

}