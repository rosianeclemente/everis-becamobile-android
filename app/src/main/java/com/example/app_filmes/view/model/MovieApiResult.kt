package com.example.app_filmes.view.model

sealed class MovieApiResult<T> {
    class Loading<T>: MovieApiResult<T>()
    class Success<T>(val data: T): MovieApiResult<T>()
    class Error<T>(val throwable: Throwable): MovieApiResult<T>()
}