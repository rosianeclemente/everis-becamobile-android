package com.example.app_filmes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_filmes.databinding.ActivityRecyclerBinding
import com.example.app_filmes.model.Filmes
import com.example.app_filmes.model.Result
import com.example.app_filmes.service.RetrofitApi
import com.example.app_filmes.service.RetrofitCliente
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRecyclerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val remote = RetrofitCliente.createService(RetrofitApi::class.java)
        val call: Call<Filmes> = remote.getListFilmes()

        val response = call.enqueue(object : Callback<Filmes> {


            override fun onFailure(call: Call<Filmes>, t: Throwable) {
               val s = t.message
            }

            override fun onResponse(call: Call<Filmes>, response: Response<Filmes>) {
                val s = response.body()

            }

        })


    }


}

