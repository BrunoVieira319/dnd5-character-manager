package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class Races(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Result(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}