package com.paparazziapps.mvp_smile_room.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paparazziapps.mvp_smile_room.models.Comentario
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import com.paparazziapps.mvp_smile_room.repository.ComentarioRepository
import kotlinx.coroutines.flow.Flow

class ExtraInfoActividadViewModel(private val repositoryComentario: ComentarioRepository): ViewModel() {


    suspend fun insertComentario(comentario: Comentario)
    {
        repositoryComentario.insert(comentario)
    }

    fun showFullComments(codigo:Int): Flow<List<Comentario>>
    {
        return repositoryComentario.showAllComents(codigo)
    }


}

class ExtraInfoActividadViewModelFactory(private val repository: ComentarioRepository): ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ExtraInfoActividadViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return ExtraInfoActividadViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }


}