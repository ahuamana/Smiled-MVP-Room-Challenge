package com.paparazziapps.mvp_smile_room.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.databinding.CardviewActividadBinding
import com.paparazziapps.mvp_smile_room.databinding.CardviewAddActivityBinding
import com.paparazziapps.mvp_smile_room.models.Actividad
import kotlinx.coroutines.withContext
import java.util.ArrayList
import kotlin.random.Random

//¿Recuerdas la clase DiffCallback que significa?
//Este es solo un objeto que ayuda a ListAdapter a determinar qué elementos de las listas nuevas y anteriores son diferentes cuando se actualiza la lista.

class Adapter (private val onItemClickListener: (Actividad) -> Unit) : ListAdapter<Actividad, Adapter.MyViewHolder>(DiffCallback)  {



    // DiffCallback
    companion object{

        private var lista: List<Actividad> = ArrayList<Actividad>()

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

    fun setList(list: List<Actividad>)
    {
        lista = list
    }

    fun getList() : List<Actividad>
    {
        return lista
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val viewHolder = MyViewHolder( CardviewActividadBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        viewHolder.itemView.setOnClickListener {

            val position = viewHolder.adapterPosition
            onItemClickListener(getItem(position))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //All code here
        holder.bind(getItem(position), position)
    }


   class MyViewHolder(private var binding: CardviewActividadBinding) : RecyclerView.ViewHolder(binding.root)
   {
       @SuppressLint ("SimpleDateFormat")
       fun bind(actividad: Actividad, position: Int)
       {
           val new=Random.nextInt(0,20)
           binding.titulo.text  = (actividad.titulo + " - ${new}")
           binding.fechaInicio.text = actividad.fecha_inicio.toString()
           binding.fechaFin.text = actividad.fecha_fin
           binding.descripcion.text = actividad.contenido

           var lastElement = lista.size - position

           if(lastElement<10)
           {
               binding.activityNumber.text = "0${lista.size - position}"
               //Log.e("TAG MENOR","Lista"+ position )
               //Log.e("TAG TITULO"," TITULO: ${binding.titulo.text.toString()}  POSITTION: ${position}  LISTA.SIZE= ${lista.size}")
           }else
           {
               binding.activityNumber.text = "${lista.size - position}"
               //Log.e("TAG MAYOR","Lista"+ position )
               //Log.e("TAG TITULO"," TITULO: ${binding.titulo.text.toString()}  POSITTION: ${position}  LISTA.SIZE= ${lista.size}")
           }

           //Log.e("TAG","Lista"+ lista.size )

       }
   }
}