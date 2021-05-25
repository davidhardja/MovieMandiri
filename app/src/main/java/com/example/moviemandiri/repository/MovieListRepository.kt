package com.example.moviemandiri.repository

import com.example.moviemandiri.model.MovieResponse
import retrofit2.Response

interface MovieListRepository {

    suspend fun getMovieList(genreId: Int, page: Int): Response<MovieResponse>
}