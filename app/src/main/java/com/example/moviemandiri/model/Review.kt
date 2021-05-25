package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Review : Serializable {

    @SerializedName("author")
    var author: String = ""

    @SerializedName("author_details")
    var authorDetail: AuthorDetail = AuthorDetail()

    @SerializedName("content")
    var content: String = ""

    @SerializedName("created_at")
    var createdAt: String = ""

    @SerializedName("id")
    var id: String = ""

    @SerializedName("updated_at")
    var updatedAt: String = ""

    @SerializedName("url")
    var url: String = ""
}