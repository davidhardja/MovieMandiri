package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.MovieDetail

interface MovieDetailUseCase {

    suspend fun getMovieDetail(id: Int): ResultState<MovieDetail>
}