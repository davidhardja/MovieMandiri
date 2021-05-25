package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.MovieDetail
import com.example.moviemandiri.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailUseCaseImpl @Inject constructor(
    private val repository: MovieDetailRepository
) : MovieDetailUseCase {

    override suspend fun getMovieDetail(id: Int): ResultState<MovieDetail> {
        val result = repository.getMovieDetail(id)
        return if (result.isSuccessful) {
            ResultState.Success(result.body() ?: MovieDetail())
        } else {
            ResultState.Error(null, result.errorBody().toString())
        }
    }
}