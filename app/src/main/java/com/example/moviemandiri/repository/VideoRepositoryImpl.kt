package com.example.moviemandiri.repository

import com.example.moviemandiri.BuildConfig
import com.example.moviemandiri.model.VideoResponse
import com.example.moviemandiri.network.NetworkService
import retrofit2.Response
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : VideoRepository {

    override suspend fun getVideo(id: Int): Response<VideoResponse> {
        return networkService.getMovieVideo(id, BuildConfig.API_KEY)
    }
}