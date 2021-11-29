package com.paparazziapps.mvp_smile_room.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.paparazziapps.mvp_smile_room.appdatabase.AppDatabase
import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: ActividadRepository) : ViewModel() {

    lateinit var allActividades: MutableLiveData<List<Actividad>>
    //val actividadDao: ActividadDao

    init {
        allActividades = MutableLiveData()
        //actividadDao = AppDatabase.getInstanceAppDatabase(getApplication())?.ActividadDao()!!
    }

    fun allactividades(): List<Actividad> = repository.getall()








}