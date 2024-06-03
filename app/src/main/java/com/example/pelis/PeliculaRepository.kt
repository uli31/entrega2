package com.example.pelis

import PeliculaDao
import androidx.lifecycle.LiveData



class PeliculaRepository(private val peliculaDao: PeliculaDao) {
    val peliculas: LiveData<List<Pelicula>> = peliculaDao.getPeliculas()

    suspend fun insertPelicula(pelicula: Pelicula) {
        peliculaDao.insertPelicula(pelicula)
    }

    suspend fun updatePelicula(pelicula: Pelicula) {
        peliculaDao.updatePelicula(pelicula)
    }

    suspend fun deletePelicula(pelicula: Pelicula) {
        peliculaDao.deletePelicula(pelicula)
    }
}
