package com.paparazziapps.mvp_smile_room.ViewModels

import androidx.lifecycle.*
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(private val repository: ActividadRepository) : ViewModel() {


    fun allactividades(): Flow<List<Actividad>>
    {
        return repository.getall()
    }

    fun allNotCompletedActividades(): Flow<List<Actividad>>
    {
        return repository.getallNotCompleted()
    }


    suspend fun insert(actividad: Actividad)
    {
        repository.insert(actividad)
    }

    fun updateIsCompleted(isCompleted:Boolean, codigo:Int)
    {
        repository.updateIsCompleted(isCompleted, codigo)
    }


}

class MainActivityViewModelFactory(private val repository: ActividadRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}