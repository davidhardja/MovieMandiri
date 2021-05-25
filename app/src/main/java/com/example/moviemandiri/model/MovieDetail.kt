package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieDetail : Serializable {

    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("budget")
    var budget: Int = 0

    @SerializedName("genres")
    var genres: List<Genre> = listOf()

    @SerializedName("homepage")
    var homepage: String? = null

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("imdb_id")
    var imdbId: String? = null

    @SerializedName("original_language")
    var originalLanguage: String = ""

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("popularity")
    var popularity: Double = 0.0

    @SerializedName("poster_path")
    var posterPath: String? = null

    @SerializedName("release_date")
    var releaseDate: String = ""

    @SerializedName("revenue")
    var revenue: Int = 0

    @SerializedName("runtime")
    var runtime: Int? = null

    @SerializedName("status")
    var status: String = ""

    @SerializedName("tagline")
    var tagline: String? = null

    @SerializedName("title")
    var title: String = ""

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("video")
    var video: Boolean = false

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
}