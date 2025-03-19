package com.example.dreamcar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes")
data class Personaje(
    @PrimaryKey()
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "ki")
    val ki: Int,
    @ColumnInfo(name = "race")
    val race: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val image: String,
) {

}
