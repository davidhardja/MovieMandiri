package com.example.moviemandiri.repository

import com.example.moviemandiri.model.ReviewResponse
import retrofit2.Response

interface ReviewRepository {

    suspend fun getReview(id: Int, page: Int): Response<ReviewResponse>
}