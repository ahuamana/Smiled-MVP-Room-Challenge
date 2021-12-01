package com.paparazziapps.mvp_smile_room.repository

import com.paparazziapps.mvp_smile_room.interfaces.ComentarioDao
import com.paparazziapps.mvp_smile_room.models.Comentario
import kotlinx.coroutines.flow.Flow

class ComentarioRepository(private val comentarioDao: ComentarioDao) {


    suspend fun insert(comentario: Comentario)
    {
        comentarioDao.crearComentario(comentario)
    }

    fun showAllComents(codigo:Int): Flow<List<Comentario>>
    {
      return  comentarioDao.showAllComents(codigo)
    }

}