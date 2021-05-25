package com.example.moviemandiri.domain.data

sealed class ResultState<T> {
    class Idle<T> : ResultState<T>()
    class Loading<T> : ResultState<T>()
    class EmptySuccess<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val data: T?, val error: String?) : ResultState<T>()
}