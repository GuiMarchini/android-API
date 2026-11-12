package com.example.rickandmortyapp.data

data class RickCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: CharacterLocation,
    val location: CharacterLocation
)

data class CharacterLocation(val name: String, val url: String)
data class CharacterResponse(val info: PageInfo, val results: List<RickCharacter>)
data class PageInfo(val count: Int, val pages: Int, val next: String?, val prev: String?)
