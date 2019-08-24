package com.fundaec.ddcharactermanager.models

class NewCharacterPostDto (
    val nameCharacter: String,
    val characterClass: String,
    val race: String,
    val attributes: List<Attribute>,
    val skills: List<Skill>
)