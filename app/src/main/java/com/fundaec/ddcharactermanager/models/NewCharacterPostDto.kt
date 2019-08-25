package com.fundaec.ddcharactermanager.models

class NewCharacterPostDto (
    val characterName: String,
    val classUrl: String,
    val raceUrl: String,
    val attributes: List<Attribute>,
    val skills: List<Skill>
)