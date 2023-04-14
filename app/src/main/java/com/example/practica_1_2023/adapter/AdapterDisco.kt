package com.example.practica_1_2023.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.practica_1_2023.R
import com.example.practica_1_2023.VistaEnDetalle
import com.example.practica_1_2023.model.disco

class AdapterDisco(var listaDisco:ArrayList<disco>,var context:Context):
    RecyclerView.Adapter<AdapterDisco.discoHolder>() {

    class discoHolder(itemView: View): ViewHolder(itemView){
        var nombreDisco: TextView
        var imagenDisco: ImageView
        var publicacionDisco: TextView

        init {
            nombreDisco = itemView.findViewById(R.id.text_nombre_discos)
            publicacionDisco= itemView.findViewById(R.id.text_info_discos)
            imagenDisco = itemView.findViewById(R.id.imagen_disco)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): discoHolder {
        val vista: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_discos,parent,false)
        return discoHolder(vista)
    }

    override fun getItemCount(): Int {
        return listaDisco.size
    }


    override fun onBindViewHolder(holder: discoHolder, position: Int) {
        val data= listaDisco[position] //por aqui podemos meter la info, la zaza
        holder.nombreDisco.text= data.nombre
        holder.publicacionDisco.text= data.publicacion
        Log.v("IMAGEN DISCO",data.grupo)
        Glide.with(context).load(data.imagen).into(holder.imagenDisco)


        holder.imagenDisco.setOnClickListener{
            val bundle=Bundle()
            val intent=Intent(context,VistaEnDetalle::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("nombreDisco",data.nombre)
            intent.putExtra("imagenDisco",data.imagen)
            intent.putExtra("publicacionDisco",data.publicacion)
            intent.putExtra("nombreGrupo",data.grupo)
            intent.putExtra("discografica",data.discografica)
            startActivity(context,intent,bundle)
        }
    }
}