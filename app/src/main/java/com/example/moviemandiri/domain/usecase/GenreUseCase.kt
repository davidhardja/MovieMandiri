package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Genre

interface GenreUseCase {

    suspend fun getGenreList(): ResultState<List<Genre>>
}