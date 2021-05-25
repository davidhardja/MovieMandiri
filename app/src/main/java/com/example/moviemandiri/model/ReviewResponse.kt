package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val page: Int,
    val results: List<Review>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)