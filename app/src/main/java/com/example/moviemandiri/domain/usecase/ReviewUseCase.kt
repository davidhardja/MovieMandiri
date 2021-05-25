package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Review

interface ReviewUseCase {

    suspend fun getReview(id: Int, page: Int): ResultState<List<Review>>
}