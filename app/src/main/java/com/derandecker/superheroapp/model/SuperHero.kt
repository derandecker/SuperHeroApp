package com.derandecker.superheroapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuperHero(
    val appearance: Appearance? = null,
    val biography: Biography? = null,
    val connections: Connections? = null,
    val id: String,
    val image: Image? = null,
    val name: String? = null,
    val powerstats: Powerstats? = null,
    val response: String? = null,
    val work: Work? = null
)

@Serializable
data class Appearance(
    @SerialName("eye-color")
    val eyeColor: String,
    val gender: String,
    @SerialName("hair-color")
    val hairColor: String,
    val height: List<String>,
    val race: String,
    val weight: List<String>
)

@Serializable
data class Biography(
    val aliases: List<String>,
    val alignment: String,
    @SerialName("alter-egos")
    val alterEgos: String,
    @SerialName("first-appearance")
    val firstAppearance: String,
    @SerialName("full-name")
    val fullName: String,
    @SerialName("place-of-birth")
    val placeOfBirth: String,
    val publisher: String
)

@Serializable
data class Connections(
    @SerialName("group-affiliation")
    val groupAffiliation: String,
    val relatives: String
)

@Serializable
data class Image(
    val url: String
)

@Serializable
data class Powerstats(
    val combat: String,
    val durability: String,
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

@Serializable
data class Work(
    val base: String,
    val occupation: String
)
