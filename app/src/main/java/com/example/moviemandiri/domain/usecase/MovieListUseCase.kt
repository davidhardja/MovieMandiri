package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Movie

interface MovieListUseCase {

    suspend fun getMovieList(genreId: Int, page: Int): ResultState<List<Movie>>
}