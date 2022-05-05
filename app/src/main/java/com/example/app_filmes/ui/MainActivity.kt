package com.example.app_filmes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app_filmes.Constans.Companion.BASE_URL
import com.example.app_filmes.adapter.ResultAdapt
import com.example.app_filmes.databinding.ActivityMainBinding
import com.example.app_filmes.model.Filmes
import com.example.app_filmes.model.Result
import com.example.app_filmes.service.IRetrofitApi
import com.example.app_filmes.service.RetrofitCliente
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val filmesClient: IRetrofitApi by lazy {
        retrofit.create(IRetrofitApi::class.java)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val filmesAdapter by lazy {
        ResultAdapt(onClickListener = { filmeId ->
            filmesDetalhes(filmeId)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.filmesRecyclerView.adapter = filmesAdapter


        val remote = RetrofitCliente.createService(IRetrofitApi::class.java)


            val call: Call<Filmes> = remote.getListFilmes()

            val response = call.enqueue(object : Callback<Filmes> {


                override fun onFailure(call: Call<Filmes>, t: Throwable) {
                    val s = t.message
                }

                override fun onResponse(call: Call<Filmes>, response: Response<Filmes>) {
                    val s = response.body()

                    getFilmes(s)

                }

            })



    }

    private fun getFilmes(filme: Filmes?) {

        var listFilmes: List<Result>

        val result = filmesClient.getListFilmes()
        filme?.results?.let { setListFilmes(it) }
    }


    private fun filmesDetalhes(filmeId: Int) {
        val intent = Intent(this, FilmesDetalhesActivity::class.java)
        intent.putExtra("filmeId", filmeId)
        startActivity(intent)
    }

    private fun setListFilmes(list: List<Result>) {
        filmesAdapter.submitList(list)
    }


}

