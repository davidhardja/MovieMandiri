package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Movie
import com.example.moviemandiri.repository.MovieListRepository
import javax.inject.Inject

class MovieListUseCaseImpl @Inject constructor(
    private val repository: MovieListRepository
) : MovieListUseCase {

    override suspend fun getMovieList(genreId: Int, page: Int): ResultState<List<Movie>> {
        val result = repository.getMovieList(genreId, page)
        return if (result.isSuccessful) {
            ResultState.Success(result.body()?.results ?: listOf())
        } else {
            ResultState.Error(null, "Terjadi Kesalahan Silahkan Ulangi Lagi")
        }
    }
}