package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Genre
import com.example.moviemandiri.repository.GenreRepository
import javax.inject.Inject

class GenreUseCaseImpl @Inject constructor(
    private val repository: GenreRepository
) : GenreUseCase {

    override suspend fun getGenreList(): ResultState<List<Genre>> {
        val result = repository.getGenreList()
        return if (result.isSuccessful) {
            if (result.body()?.genres?.isNotEmpty() == true) {
                ResultState.Success(result.body()?.genres ?: listOf())
            } else {
                ResultState.EmptySuccess()
            }
        } else {
            ResultState.Error(null, "Terjadi Kesalahan Silahkan Ulangi Lagi")
        }
    }
}