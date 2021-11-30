package com.paparazziapps.mvp_smile_room.repository

import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.flow.Flow

class ActividadRepository(private val dao: ActividadDao) {

    val actividades = dao.getAllActividades()

    suspend fun insert(actividad: Actividad)
    {
        dao.crearActividad(actividad)
    }

    suspend fun update(actividad: Actividad)
    {
        dao.updateActividad(actividad)
    }

    suspend fun delete(actividad: Actividad)
    {
        dao.deleteActividad(actividad)
    }

    suspend fun deleteAll()
    {
        dao.deteleAll()
    }

    fun getall() : Flow<List<Actividad>> = dao.getAllActividades()


}