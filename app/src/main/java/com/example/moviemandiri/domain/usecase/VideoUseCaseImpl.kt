package com.example.moviemandiri.domain.usecase

import com.example.moviemandiri.domain.data.ResultState
import com.example.moviemandiri.model.Video
import com.example.moviemandiri.repository.VideoRepository
import javax.inject.Inject

class VideoUseCaseImpl @Inject constructor(
    private val repository: VideoRepository
) : VideoUseCase {

    override suspend fun getVideo(id: Int): ResultState<List<Video>> {
        val result = repository.getVideo(id)
        return if (result.isSuccessful) {
            ResultState.Success(result.body()?.results ?: listOf())
        } else {
            ResultState.Error(null, result.errorBody().toString())
        }
    }
}