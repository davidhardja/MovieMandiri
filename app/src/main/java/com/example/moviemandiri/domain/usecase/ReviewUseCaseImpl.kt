package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Review
import com.example.moviemandiri.repository.ReviewRepository
import javax.inject.Inject

class ReviewUseCaseImpl @Inject constructor(
    private val repository: ReviewRepository
) : ReviewUseCase {

    override suspend fun getReview(id: Int, page: Int): ResultState<List<Review>> {
        val result = repository.getReview(id, page)
        return if (result.isSuccessful) {
            ResultState.Success(result.body()?.results ?: listOf())
        } else {
            ResultState.Error(null, "Terjadi Kesalahan Silahkan Ulangi Lagi")
        }
    }
}