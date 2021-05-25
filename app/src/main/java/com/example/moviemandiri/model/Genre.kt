package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Genre: Serializable {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""
}