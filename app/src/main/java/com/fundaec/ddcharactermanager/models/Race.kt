package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class Race(
    @SerializedName("ability_bonuses")
    val abilityBonuses: List<Int>,
    @SerializedName("age")
    val age: String,
    @SerializedName("alignment")
    val alignment: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("index")
    val index: Int,
    @SerializedName("language_desc")
    val languageDesc: String,
    @SerializedName("languages")
    val languages: List<Language>,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: String,
    @SerializedName("size_description")
    val sizeDescription: String,
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("starting_proficiencies")
    val startingProficiencies: List<StartingProficiency>,
    @SerializedName("starting_proficiency_options")
    val startingProficiencyOptions: StartingProficiencyOptions,
    @SerializedName("subraces")
    val subraces: List<Subrace>,
    @SerializedName("traits")
    val traits: List<Trait>,
    @SerializedName("url")
    val url: String
) {
    data class Subrace(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class StartingProficiencyOptions(
        @SerializedName("choose")
        val choose: Int,
        @SerializedName("from")
        val from: List<From>,
        @SerializedName("type")
        val type: String
    ) {
        data class From(
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )
    }

    data class Language(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class StartingProficiency(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class Trait(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}