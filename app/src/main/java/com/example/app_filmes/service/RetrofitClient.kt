package com.example.app_filmes.service

import com.example.app_filmes.Constans.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
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

        val MOVIE_SERVICE: IRetrofitClient by lazy {
            getRetrofitInstance().create(IRetrofitClient::class.java)
        }

    }

}