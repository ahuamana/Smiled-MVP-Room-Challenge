package com.paparazziapps.mvp_smile_room.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paparazziapps.mvp_smile_room.R
import com.paparazziapps.mvp_smile_room.databinding.CardviewActividadBinding
import com.paparazziapps.mvp_smile_room.models.Actividad

class ActividadAdapter(context: Context) : RecyclerView.Adapter<ActividadAdapter.ViewHolder>() {

    var context= context
    var actidadesList = ArrayList<Actividad>()

    fun setListActidades(data:ArrayList<Actividad>)
    {
        this.actidadesList =data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemview = LayoutInflater.from(context).inflate(R.layout.cardview_actividad, parent, false)

        return ViewHolder(itemview)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind( actidadesList[position])
    }

    override fun getItemCount(): Int {
       return actidadesList.size
    }

    //connect
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = CardviewActividadBinding.bind(view)

        fun bind(actividad: Actividad)
        {
            //All code here
            binding.titulo.text = actividad.titulo

        }

    }



}