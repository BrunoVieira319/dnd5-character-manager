package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class CharacterMainDto(
    @SerializedName("characterClass")
    val characterClass: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("nameCharacter")
    val nameCharacter: String,
    @SerializedName("race")
    val race: String
)