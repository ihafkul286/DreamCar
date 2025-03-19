package com.example.dreamcar.datamodel

import com.example.dreamcar.model.Personaje
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class FullPersonaje(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("ki") val ki: Int,
    @SerialName("race") val race: String,
    @SerialName("description") val description: String,
    @SerialName("image") val image: String
){
    fun toPersonaje() = Personaje(id, name, ki, race, description, image)
}
