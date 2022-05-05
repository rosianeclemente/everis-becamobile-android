package com.example.app_filmes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app_filmes.databinding.ActivityFilmeDetalhesBinding
import com.example.app_filmes.databinding.ActivityListItemBinding
import com.example.app_filmes.model.Filmes
import com.example.app_filmes.model.Result
import com.squareup.picasso.Picasso

class FilmesDetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFilmeDetalhesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filmeId = intent.getIntExtra("filmeId", 0)



    }

    private fun setDados(filmeSelecionado: Result?) {
        filmeSelecionado?.let {
            Glide.with(binding.root.context)
                .load(it.getImagemCapa()) //precisa passar url?
                .centerCrop()
                .into(binding.filmeDetalhes)
            binding.nomeFilmeDetalhes.text = it.title
            binding.caracteristicasFilmeDetalhes.text = it.overview
        }

    }
}