package com.example.moviemandiri.repository

import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.model.GenreResponse
import com.example.moviemandiri.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : GenreRepository {

    override suspend fun getGenreList(): Response<GenreResponse> {
        return networkService.getGenreList(BuildConfig.API_KEY)
    }
}