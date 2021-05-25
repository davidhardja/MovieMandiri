package com.example.moviemandiri.repository

import com.example.moviemandiri.model.MovieDetail
import retrofit2.Response

interface MovieDetailRepository {

    suspend fun getMovieDetail(id: Int): Response<MovieDetail>
}