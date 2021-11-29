package com.paparazziapps.mvp_smile_room.ViewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModel
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository

//To create differents viewModels

class MainActivityViewModelFactory(private val repository: ActividadRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}