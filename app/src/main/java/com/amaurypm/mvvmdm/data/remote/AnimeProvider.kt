package com.amaurypm.mvvmdm.data.remote

import com.amaurypm.mvvmdm.data.remote.model.AnimeDto
import kotlinx.coroutines.delay

//Simulando mi conexión al backend con retrofit
class AnimeProvider {
    companion object {

        private val animes = mutableListOf<AnimeDto>()

        init {
            //Genero una lista con 30 animes
            for (i in 1 .. 50) {
                animes.add(
                    AnimeDto(
                        titulo = "Título $i",
                        fecha = "Fecha $i",
                        id = i.toLong(),
                        tipo = "Tipo $i"
                    )
                )
            }
        }

        suspend fun getAnimesApi(): AnimeDto{
            val position = (0..49).random()
            delay((1000..4000).random().toLong())
            return animes[position]
        }

    }
}