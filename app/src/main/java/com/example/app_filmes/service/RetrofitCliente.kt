package com.example.app_filmes.service

import com.example.app_filmes.Constans.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitCliente {
    companion object {
        private lateinit var retrofit: Retrofit
        private val baseUrl = BASE_URL

        fun getRetrofitInstance(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        val movieService: IRetrofitApi by lazy {
            getRetrofitInstance().create(IRetrofitApi::class.java)
        }

    }

}