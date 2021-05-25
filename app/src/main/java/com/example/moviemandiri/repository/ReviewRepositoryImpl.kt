package com.example.moviemandiri.repository

import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.model.ReviewResponse
import com.example.moviemandiri.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : ReviewRepository {

    override suspend fun getReview(id: Int, page: Int): Response<ReviewResponse> {
        return networkService.getMovieReview(id, BuildConfig.API_KEY, page)
    }
}