package com.paparazziapps.mvp_smile_room.ViewModels

import androidx.lifecycle.*
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(private val repository: ActividadRepository) : ViewModel() {

    /*
    lateinit var allActividades: Flow<List<Actividad>>
    //val actividadDao: ActividadDao

    init {
         allActividades = MutableLiveData()
        //actividadDao = AppDatabase.getInstanceAppDatabase(getApplication())?.ActividadDao()!!
    }
    */

    fun allactividades(): Flow<List<Actividad>> = repository.getall()


    suspend fun insert(actividad: Actividad)
    {
        repository.insert(actividad)
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