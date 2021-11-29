package com.paparazziapps.mvp_smile_room.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.paparazziapps.mvp_smile_room.models.Actividad

@Dao
interface ActividadDao {

    @Insert
    suspend fun crearActividad(actividad: Actividad)

    @Update
    suspend fun updateActividad(actividad: Actividad)

    @Delete
    suspend fun deleteActividad(actividad: Actividad)

    @Query("Delete from table_actividad")
    suspend fun deteleAll()

    @Query("Select * from table_actividad order by fecha_inicio desc")
    fun getAllActividades(): List<Actividad>
}