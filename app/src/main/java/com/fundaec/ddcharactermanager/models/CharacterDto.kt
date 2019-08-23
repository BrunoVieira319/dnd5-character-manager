package com.fundaec.ddcharactermanager.models

class CharacterDto (
    val nameCharacter: String,
    val characterClass: String,
    val race: String,
    val attributes: List<Attribute>,
    val skills: List<Skill>
)