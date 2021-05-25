package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Video

interface VideoUseCase {

    suspend fun getVideo(id: Int): ResultState<List<Video>>
}