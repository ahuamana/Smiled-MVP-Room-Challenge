package com.paparazziapps.mvp_smile_room.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.activities.ExtraInfoActividadActivity
import com.paparazziapps.mvp_smile_room.activities.MainActivity
import com.paparazziapps.mvp_smile_room.databinding.CardviewActividadBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.ArrayList
import kotlin.random.Random

//¿Recuerdas la clase DiffCallback que significa?
//Este es solo un objeto que ayuda a ListAdapter a determinar qué elementos de las listas nuevas y anteriores son diferentes cuando se actualiza la lista.

class Adapter (private val onItemClickListener: (Actividad) -> Unit ,private val context: Context) : ListAdapter<Actividad, Adapter.MyViewHolder>(DiffCallback)  {

    // DiffCallback
    companion object{


        private val DiffCallback = object : DiffUtil.ItemCallback<Actividad>()
        {
            //verifica si el objeto (o la fila de la base de datos, en tu caso) es el mismo que verifica el ID
            override fun areItemsTheSame(oldItem: Actividad, newItem: Actividad): Boolean {
                return oldItem.codigo == newItem.codigo
            }

            //verifica si todas las propiedades, no solo el ID, son iguales.
            override fun areContentsTheSame(oldItem: Actividad, newItem: Actividad): Boolean {
                return oldItem == newItem
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val viewHolder = MyViewHolder( CardviewActividadBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //All code here
        holder.bind(getItem(position), position, context, itemCount)
    }


   class MyViewHolder(private var binding: CardviewActividadBinding) : RecyclerView.ViewHolder(binding.root)
   {
       @SuppressLint ("SimpleDateFormat")
       fun bind(actividad: Actividad, position: Int, context: Context, cantidad:Int)
       {
           val new=Random.nextInt(0,20)
           binding.titulo.text  = (actividad.titulo + " - ${new}")
           binding.fechaInicio.text = SimpleDateFormat("dd/MM/yyyy").format(actividad.fecha_inicio)
           binding.fechaFin.text = actividad.fecha_fin
           binding.descripcion.text = actividad.contenido

           var lastElement:Int = cantidad - position

           if(lastElement<10)
           {
               binding.activityNumber.text = "0${lastElement}"
               //Log.e("TAG MENOR","Lista"+ position )
               Log.e("TAG TITULO"," TITULO: ${binding.titulo.text.toString()}  POSITTION: ${position}  LISTA.SIZE= ${cantidad}")
               Log.e("TAG", "LASTELEMENT: ${lastElement}")
           }else
           {
               binding.activityNumber.text = "${lastElement}"
               //Log.e("TAG MAYOR","Lista"+ position )
               Log.e("TAG TITULO"," TITULO: ${binding.titulo.text.toString()}  POSITTION: ${position}  LISTA.SIZE= ${cantidad}")
               Log.e("TAG", "LASTELEMENT: ${lastElement}")
           }

           binding.cardview.setOnClickListener {
               var intent = Intent(context,ExtraInfoActividadActivity::class.java).apply {
                   putExtra("codigo",actividad.codigo)
                   putExtra("titulo",actividad.titulo)
                   putExtra("contenido",actividad.contenido)
                   putExtra("fecha_inicio",SimpleDateFormat("dd/MM/yyyy").format(actividad.fecha_inicio))
                   putExtra("fecha_fin",actividad.fecha_fin)
               }
               context.startActivity(intent)
           }


           if(actividad.isCompleted)
           {
               binding.checkBox.isChecked = true
           }else
           {
               if(!actividad.isCompleted)
               {
                   binding.checkBox.isChecked = false
               }
           }


           binding.checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
               Log.e("TAG"," CHECCKED CHANGE: ${isChecked}")
               Log.e("TAG","HICISTE CLICK EN CHECKBOX")

               (context as MainActivity).updateIsCompleted(isChecked,actividad.codigo)

           }

           //updateisCompletedActividad()

       }

       private fun updateisCompletedActividad() {

           binding.checkBox.setOnClickListener {

               Log.e("TAG","HICISTE CLICK EN CHECKBOX")
               var isChecked = binding.checkBox.isChecked

               if(isChecked)
               {
                   !isChecked

               }else
               {
                   !isChecked
               }

           }
       }
   }

}