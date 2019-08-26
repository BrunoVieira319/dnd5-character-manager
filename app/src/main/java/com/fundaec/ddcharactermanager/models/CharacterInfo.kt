package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class CharacterInfo(
    @SerializedName("attributes")
    val attributes: List<Attribute>,
    @SerializedName("characterClass")
    val characterClass: String,
    @SerializedName("characterName")
    val characterName: String,
    @SerializedName("classUrl")
    val classUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("level")
    val level: Int,
    @SerializedName("proficiencyBonus")
    val proficiencyBonus: Int,
    @SerializedName("race")
    val race: String,
    @SerializedName("raceUrl")
    val raceUrl: String,
    @SerializedName("skills")
    val skills: List<Skill>
) {
    data class Skill(
        @SerializedName("name")
        val name: String,
        @SerializedName("proficient")
        val proficient: Boolean,
        @SerializedName("value")
        val value: Int
    )

    data class Attribute(
        @SerializedName("abbreviationName")
        val abbreviationName: String,
        @SerializedName("modifier")
        val modifier: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: Int
    )
}