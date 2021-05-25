package com.example.moviemandiri.repository

import com.example.moviemandiri.model.GenreResponse
import retrofit2.Response

interface GenreRepository {

    suspend fun getGenreList(): Response<GenreResponse>
}