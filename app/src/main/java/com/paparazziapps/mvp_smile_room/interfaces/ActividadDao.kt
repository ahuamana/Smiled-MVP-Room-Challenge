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

    @Query("Select * from table_actividad where isCompleted= 0 order by codigo desc")
    fun getAllCompletedActividades(): Flow<List<Actividad>>

    @Query("Select * from table_actividad where isCompleted= 1 order by codigo desc")
    fun getAllNotCompletedActividades() : Flow<List<Actividad>>

    @Query("update table_actividad set isCompleted= :iscompleted where codigo = :codigo")
    fun changeIsCompleted(iscompleted: Boolean, codigo: Int)
}