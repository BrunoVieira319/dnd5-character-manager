package com.fundaec.ddcharactermanager.models


import com.google.gson.annotations.SerializedName

data class CharacterClass(
    @SerializedName("class_levels")
    val classLevels: ClassLevels,
    @SerializedName("hit_die")
    val hitDie: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("index")
    val index: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("proficiencies")
    val proficiencies: List<Proficiency>,
    @SerializedName("proficiency_choices")
    val proficiencyChoices: List<ProficiencyChoice>,
    @SerializedName("saving_throws")
    val savingThrows: List<SavingThrow>,
    @SerializedName("starting_equipment")
    val startingEquipment: StartingEquipment,
    @SerializedName("subclasses")
    val subclasses: List<Subclasse>,
    @SerializedName("url")
    val url: String
) {
    data class SavingThrow(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class Proficiency(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )

    data class StartingEquipment(
        @SerializedName("class")
        val classX: String,
        @SerializedName("url")
        val url: String
    )

    data class ClassLevels(
        @SerializedName("class")
        val classX: String,
        @SerializedName("url")
        val url: String
    )

    data class ProficiencyChoice(
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

    data class Subclasse(
        @SerializedName("name")
        val name: String,
        @SerializedName("url")
        val url: String
    )
}