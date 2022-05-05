package com.example.app_filmes.model

import java.io.Serializable


data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    val media_type: String
):Serializable {

    fun getImagemCapa(): String{
        return "https://image.tmdb.org/t/p/w500/$poster_path"
    }
    fun getTitulo(): String{
        return title
    }
    fun getAvaliacao(): Double{
        return vote_average
    }
    fun getResumo(): String{
        return overview
    }

}