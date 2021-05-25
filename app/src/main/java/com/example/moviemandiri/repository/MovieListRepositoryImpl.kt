package com.example.moviemandiri.repository

import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.model.MovieResponse
import com.example.moviemandiri.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : MovieListRepository {

    override suspend fun getMovieList(genreId: Int, page: Int): Response<MovieResponse> {
        return networkService.getDiscoverMovieByGenre(
            BuildConfig.API_KEY,
            page,
            genreId,
            "popularity.desc",
            false,
            false,
            "flatrate"
        )
    }
}