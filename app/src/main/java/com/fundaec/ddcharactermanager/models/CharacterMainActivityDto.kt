package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class CharacterMainActivityDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("characterClass")
    val characterClass: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("characterName")
    val characterName: String,
    @SerializedName("race")
    val race: String,
    @SerializedName("image")
    val image: String
)