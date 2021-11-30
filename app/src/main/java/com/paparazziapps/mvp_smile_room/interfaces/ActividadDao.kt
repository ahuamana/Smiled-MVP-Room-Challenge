package com.paparazziapps.mvp_smile_room.interfaces

import androidx.room.*
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.flow.Flow

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

    @Query("Select * from table_actividad order by codigo desc")
    fun getAllActividades(): Flow<List<Actividad>>
}