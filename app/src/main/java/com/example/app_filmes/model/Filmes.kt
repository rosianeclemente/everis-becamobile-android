package com.example.app_filmes.model

import java.io.Serializable

class Filmes(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)