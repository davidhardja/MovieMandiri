package com.example.moviemandiri.network

import com.example.moviemandiri.model.GenreResponse
import com.example.moviemandiri.model.MovieDetail
import com.example.moviemandiri.model.MovieDetailResponse
import com.example.moviemandiri.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

    @GET("genre/movie/list")
    suspend fun getGenreList(
        @Query("api_key") key: String
    ): Response<GenreResponse>

    @GET("discover/movie")
    suspend fun getDiscoverMovieByGenre(
        @Query("api_key") key: String,
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: Boolean,
        @Query("include_video") includeVideo: Boolean,
        @Query("with_watch_monetization_types") types: String
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") key: String
    ): Response<MovieDetailResponse>
}