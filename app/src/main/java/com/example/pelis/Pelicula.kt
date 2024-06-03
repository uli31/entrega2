package com.example.pelis
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var titulo: String,
    var genero: String,
    var año: Int,
    var valoracion: Float
)