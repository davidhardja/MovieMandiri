package com.example.moviemandiri.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Video : Serializable {

    @SerializedName("id")
    var id: String = ""

    @SerializedName("key")
    var key: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("site")
    var site: String = ""

    @SerializedName("size")
    var size: String = ""

    @SerializedName("type")
    var type: String = ""
}