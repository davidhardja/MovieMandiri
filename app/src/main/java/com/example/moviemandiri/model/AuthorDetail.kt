package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AuthorDetail: Serializable {

    @SerializedName("name")
    var name: String = ""

    @SerializedName("username")
    var username: String = ""

    @SerializedName("avatar_path")
    var avatarPath: String? = null

    @SerializedName("rating")
    var rating: Int? = null
}