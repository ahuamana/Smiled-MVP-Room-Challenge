package com.paparazziapps.mvp_smile_room.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.models.Comentario
import kotlinx.coroutines.flow.Flow

@Dao
interface ComentarioDao {

    @Insert
    suspend fun crearComentario(comentario: Comentario)

    @Query("Select * from table_comentarios where codigo_actividad=:codigoReceived order by unixtime desc")
    fun showAllComents(codigoReceived:Int): Flow<List<Comentario>>


}