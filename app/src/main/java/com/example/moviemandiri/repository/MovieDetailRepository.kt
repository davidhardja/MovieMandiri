package com.example.moviemandiri.repository

import com.example.moviemandiri.model.MovieDetailResponse
import retrofit2.Response

interface MovieDetailRepository {

    suspend fun getMovieDetail(id: Int): Response<MovieDetailResponse>
}