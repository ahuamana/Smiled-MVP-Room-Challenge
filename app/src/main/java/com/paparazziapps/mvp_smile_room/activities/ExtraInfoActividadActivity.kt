package com.paparazziapps.mvp_smile_room.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.R
import com.paparazziapps.mvp_smile_room.ViewModels.ExtraInfoActividadViewModel
import com.paparazziapps.mvp_smile_room.ViewModels.ExtraInfoActividadViewModelFactory
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModel
import com.paparazziapps.mvp_smile_room.ViewModels.MainActivityViewModelFactory
import com.paparazziapps.mvp_smile_room.adapters.Adapter
import com.paparazziapps.mvp_smile_room.adapters.ComentarioAdapter
import com.paparazziapps.mvp_smile_room.appdatabase.ActividadAplication
import com.paparazziapps.mvp_smile_room.databinding.ActivityExtraInfoActividadBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewEliminarActividadBinding
import com.paparazziapps.mvp_smile_room.models.Actividad
import com.paparazziapps.mvp_smile_room.models.Comentario
import com.paparazziapps.mvp_smile_room.repository.ActividadRepository
import com.paparazziapps.mvp_smile_room.repository.ComentarioRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ExtraInfoActividadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraInfoActividadBinding

    private lateinit var repositoryactividad: ActividadRepository
    private lateinit var repositorycomentario: ComentarioRepository
    private lateinit var viewmodelActividad: MainActivityViewModel
    private lateinit var viewmodelComentario: ExtraInfoActividadViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var mComentarioAdapter:ComentarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraInfoActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //all code here
        setDataForIntent()

        repositoryactividad = ActividadRepository((application as ActividadAplication).database.ActividadDao())
        repositorycomentario = ComentarioRepository((application as ActividadAplication).database.ComentarioDao())
        viewmodelActividad = ViewModelProvider(this, MainActivityViewModelFactory(repositoryactividad)).get(MainActivityViewModel::class.java)
        viewmodelComentario = ViewModelProvider(this, ExtraInfoActividadViewModelFactory(repositorycomentario)).get(ExtraInfoActividadViewModel::class.java)

        createComment()

        deleteActividad()

        openDialog()

        showAllCommentsByCodigo()

    }

    private fun showAllCommentsByCodigo() {

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        mComentarioAdapter = ComentarioAdapter({}, this)

        recyclerView.adapter = mComentarioAdapter



        lifecycle.coroutineScope.launch {
            viewmodelComentario.showFullComments(intent.getIntExtra("codigo",9999)).collect {
                mComentarioAdapter.submitList(it)
            }
        }
    }

    private fun createComment() {
        binding.send.setOnClickListener {

            Log.e("MENSAJE",binding.editTextMessage.text.toString())

            var comentario = Comentario()
            comentario.codigo_actividad =  intent.getIntExtra("codigo",9999)
            comentario.mensaje =  binding.editTextMessage.text.toString()
            comentario.unixtime = System.currentTimeMillis()

                lifecycleScope.launch {
                    viewmodelComentario.insertComentario(comentario)

                }

            binding.editTextMessage.setText("")

        }
    }

    private fun deleteActividad() {

        binding.mytoolbar.delete.setOnClickListener {

            val dialog = Dialog(this)

            var binding2: CardviewEliminarActividadBinding
            binding2 = CardviewEliminarActividadBinding.inflate(LayoutInflater.from(applicationContext))

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(binding2.root)

            binding2.name.setText("¿Seguro quieres eliminar la ${binding.mytoolbar.title.text}?")

            binding2.cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            binding2.okButton.setOnClickListener {

                lifecycleScope.launch {
                    viewmodelActividad.deleteActividadByCodigo(intent.getIntExtra("codigo",9999))
                }
                dialog.dismiss()
                finish()

            }



            dialog.show()


        }
    }

    private fun openDialog() {

        binding.mytoolbar.edit.setOnClickListener {

            val dialog = Dialog(this)

            var binding2: CardviewAddActivityBinding
            binding2 = CardviewAddActivityBinding.inflate(LayoutInflater.from(applicationContext))

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(binding2.root)

            binding2.titulo.setText(binding.mytoolbar.title.text)
            binding2.contenido.setText(binding.descripcion.text)
            binding2.fechaFin.setText(binding.fechaFin.text)

            binding2.cancelButton.setOnClickListener {
                dialog.dismiss()
            }

            binding2.okButton.setOnClickListener {



                var actividad = Actividad()
                actividad.codigo = intent.getIntExtra("codigo",9999)
                actividad.titulo = binding2.titulo.text.toString()
                actividad.contenido = binding2.contenido.text.toString()
                actividad.fecha_fin = binding2.fechaFin.text.toString()

                lifecycle.coroutineScope.launch {
                    viewmodelActividad.updateByCodigo(actividad)
                    dialog.dismiss()
                    updateActividad(actividad)
                }
            }

            dialog.show()

        }

    }

    private fun setDataForIntent() {

        binding.mytoolbar.title.text = intent.getStringExtra("titulo")
        binding.descripcion.text = intent.getStringExtra("contenido")
        binding.fechaInicio.text = intent.getStringExtra("fecha_inicio")
        binding.fechaFin.text = intent.getStringExtra("fecha_fin")

        binding.mytoolbar.imageVisibility.isVisible = false
        binding.mytoolbar.linearEditDelete.isVisible = true
    }

    private fun updateActividad(actividad: Actividad)
    {
        binding.mytoolbar.title.text = actividad.titulo
        binding.descripcion.text = actividad.contenido
        binding.fechaFin.text = actividad.fecha_fin
    }

    fun updateCantidadMensajes(cantidad:Int)
    {

        binding.cantidadComentarios.setText("(${cantidad})")

    }
}