package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie : Serializable {

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("overview")
    var overview: String = ""

    @SerializedName("release_date")
    var releaseDate: String = ""

    @SerializedName("genre_ids")
    var genreIds: List<Int> = listOf()

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("original_language")
    var originalLanguage: String = ""

    @SerializedName("title")
    var title: String = ""

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("popularity")
    var popularity: Double = 0.0

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
}