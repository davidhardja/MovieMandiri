package com.example.moviemandiri.repository

import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.model.MovieDetail
import com.example.moviemandiri.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : MovieDetailRepository {

    override suspend fun getMovieDetail(id: Int): Response<MovieDetail> {
        return networkService.getDetailMovie(id, BuildConfig.API_KEY)
    }
}