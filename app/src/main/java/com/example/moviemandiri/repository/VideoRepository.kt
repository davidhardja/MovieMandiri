package com.example.moviemandiri.repository

import com.example.moviemandiri.model.VideoResponse
import retrofit2.Response

interface VideoRepository {

    suspend fun getVideo(id: Int): Response<VideoResponse>
}