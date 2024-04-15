package com.amaurypm.mvvmdm.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amaurypm.mvvmdm.data.remote.AnimeProvider
import com.amaurypm.mvvmdm.data.remote.model.AnimeDto
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    //Ponemos un contenedor observable livedata
    private val _anime = MutableLiveData<AnimeDto>() //versión mutable e interna
    val anime: LiveData<AnimeDto> = _anime //esta se accede públicamente y es read-only

    //Un string livedata
    private val _name = MutableLiveData<String>() //versión mutable e interna
    val name: LiveData<String> = _name //esta se accede públicamente y es read-only

    //Para un long
    private val _numero = MutableLiveData<Long>() //versión mutable e interna
    val numero: LiveData<Long> = _numero //esta se accede públicamente y es read-only

    fun getAnime(){
        viewModelScope.launch {
            _anime.postValue(AnimeProvider.getAnimesApi())
        }
    }

    fun getName(){
        _name.postValue("Amaury")
    }

    fun getNumero(){
        _numero.postValue(500)
    }


}