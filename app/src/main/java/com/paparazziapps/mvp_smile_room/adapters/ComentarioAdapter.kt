package com.paparazziapps.mvp_smile_room.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.activities.ExtraInfoActividadActivity
import com.paparazziapps.mvp_smile_room.databinding.CardviewComentarioBinding
import com.paparazziapps.mvp_smile_room.models.Comentario

class ComentarioAdapter( private val onItemClickListener: (Comentario) -> Unit, private val context: Context) : ListAdapter<Comentario, ComentarioAdapter.myViewHolder>(DiffCallBack){


    companion object{

        private val DiffCallBack = object: DiffUtil.ItemCallback<Comentario>()
        {
            override fun areItemsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
                return oldItem.codigo == newItem.codigo
            }

            override fun areContentsTheSame(oldItem: Comentario, newItem: Comentario): Boolean {
                return oldItem == newItem
            }
        }

    }


    override fun onBindViewHolder(holder: ComentarioAdapter.myViewHolder, position: Int) {
        holder.bind(getItem(position), itemCount, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioAdapter.myViewHolder {

        val viewHolder = myViewHolder(CardviewComentarioBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClickListener(getItem(position))
        }

        return viewHolder
    }


    class myViewHolder(private var binding: CardviewComentarioBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(comentario:Comentario, cantidadList:Int, contexto: Context)
        {
            Log.e("TAG","CANTIDAD DE MENSAJES: ${cantidadList}")
            binding.message.setText(comentario.mensaje)


            (contexto as ExtraInfoActividadActivity).updateCantidadMensajes(cantidadList)

        }
    }


}