package com.example.practica_1_2023.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.practica_1_2023.ActivityDisco
import com.example.practica_1_2023.R
import com.example.practica_1_2023.model.grupo

class AdapterGrupo(var listaGrupos:ArrayList<grupo>,var context:Context):
    RecyclerView.Adapter<AdapterGrupo.grupoHolder>() {

    class grupoHolder(itemView: View) : ViewHolder(itemView) {
        var nombreGrupo: TextView
        var imagenGrupo: ImageView
        var generoGrupo: TextView

        init {
            nombreGrupo = itemView.findViewById(R.id.text_nombre_grupos)
            generoGrupo = itemView.findViewById(R.id.text_info_grupos)
            imagenGrupo = itemView.findViewById(R.id.imagen_grupo)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): grupoHolder {
        val vista: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_grupos, parent, false)
        return grupoHolder(vista)
    }

    override fun getItemCount(): Int {
        return listaGrupos.size
    }


    override fun onBindViewHolder(holder: grupoHolder, position: Int) {
        val data = listaGrupos[position]
        holder.nombreGrupo.text = data.nombre
        holder.generoGrupo.text = data.genero
        Glide.with(context).load(data.imagenGrupo).into(holder.imagenGrupo)
        holder.imagenGrupo.setOnClickListener {
            val bundle = Bundle()
            val intent = Intent(context, ActivityDisco::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("nombreGrupo", data.nombre)
            startActivity(context, intent, bundle)
        }


    }
}