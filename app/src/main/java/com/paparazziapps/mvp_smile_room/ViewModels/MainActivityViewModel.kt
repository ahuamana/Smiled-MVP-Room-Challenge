package com.paparazziapps.mvp_smile_room.ViewModels

import androidx.lifecycle.*
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(private val actividadDao: ActividadDao) : ViewModel() {

    /*
    lateinit var allActividades: Flow<List<Actividad>>
    //val actividadDao: ActividadDao

    init {
         allActividades = MutableLiveData()
        //actividadDao = AppDatabase.getInstanceAppDatabase(getApplication())?.ActividadDao()!!
    }
    */

    fun allactividades(): Flow<List<Actividad>> = actividadDao.getAllActividades()


    suspend fun insert(actividad: Actividad)
    {
        actividadDao.crearActividad(actividad)
    }

}

class MainActivityViewModelFactory(private val actividadDao: ActividadDao): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(actividadDao) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

}