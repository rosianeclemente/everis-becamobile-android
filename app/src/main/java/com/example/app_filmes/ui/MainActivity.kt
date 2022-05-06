package com.example.app_filmes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app_filmes.Constans.Companion.BASE_URL
import com.example.app_filmes.adapter.ResultAdapt
import com.example.app_filmes.databinding.ActivityMainBinding
import com.example.app_filmes.model.Movie
import com.example.app_filmes.model.Response
import com.example.app_filmes.service.IRetrofitApi
import com.example.app_filmes.service.RetrofitCliente
import retrofit2.Call
import retrofit2.Callback
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

//    private val filmesAdapter by lazy {
//        ResultAdapt()
//    }
    private val filmesAdapter by lazy {
        ResultAdapt(onClickListener = { filme ->
            filmesDetalhes(filme)
        })
    }

//    val onClickListener: OnClickListener = object :
//    OnClickListener{
////    override fun onClick(filme: Movie) {
////        chamadaDetalhes(filme)
////    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.filmesRecyclerView.adapter = filmesAdapter
        val remote = RetrofitCliente.createService(IRetrofitApi::class.java)

        val call: Call<Response> = remote.getListFilmes()
        call.enqueue(object : Callback<Response> {

            override fun onFailure(call: Call<Response>, t: Throwable) {
                val s = t.message
            }

//            override fun onResponse(call: Call<Response>, response:Response) {
//                val s = response.body()
//                getFilmes(s)
//            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val s = response.body()
                getFilmes(s)
            }

        })
    }

    private fun getFilmes(filme: Response?) {
        filmesClient.getListFilmes()
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

//    private fun chamadaDetalhes(filme: Movie){
//        var intent = Intent(this, FilmesDetalhesActivity::class.java)
//        intent.putExtra("filme", filme)
//        startActivity(intent)
//
//    }

}

