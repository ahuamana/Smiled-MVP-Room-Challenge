package com.paparazziapps.mvp_smile_room.repository

import com.paparazziapps.mvp_smile_room.interfaces.ActividadDao
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.flow.Flow

class ActividadRepository(private val dao: ActividadDao) {

    val actividades = dao

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

    fun getall() : Flow<List<Actividad>> = dao.getAllCompletedActividades()

    fun getallNotCompleted() : Flow<List<Actividad>>
    {
        return dao.getAllNotCompletedActividades()
    }

    fun updateIsCompleted(isCompleted:Boolean, codigo:Int)
    {
        dao.changeIsCompleted(isCompleted,codigo)
    }

    fun deleteActividad(codigo: Int)
    {
        dao.deleteActividad(codigo)
    }

    fun updateByCodigo(actividad: Actividad)
    {
        dao.updateActividadByCodigo(actividad.codigo,actividad.titulo,actividad.contenido,actividad.fecha_fin)
    }


}