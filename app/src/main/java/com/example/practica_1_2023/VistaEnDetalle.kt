package com.example.practica_1_2023

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.practica_1_2023.databinding.ActivityVistaEnDetalleBinding


class VistaEnDetalle : AppCompatActivity() {

    lateinit var binding: ActivityVistaEnDetalleBinding
    private lateinit var imagenDisco: String
    private lateinit var grupoDisco: String
    private lateinit var publicacionDisco: String
    private lateinit var nombreDisco: String
    private lateinit var discografica: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVistaEnDetalleBinding.inflate(layoutInflater)
        grupoDisco = intent.extras?.getString("nombreGrupo","") ?: ""
        imagenDisco = intent.extras?.getString("imagenDisco","") ?: ""
        publicacionDisco = intent.extras?.getString("publicacionDisco","") ?: ""
        nombreDisco = intent.extras?.getString("nombreDisco","") ?: ""
        discografica= intent.extras?.getString("discografica","")?:""

        setContentView(binding.root)
        putData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val asunto="Has escuchado el disco de ${grupoDisco}"
        val texto= "El disco ${nombreDisco}, fue publicado en el año ${publicacionDisco}, por la discografica ${discografica}. Deberias Escucharlo!!"
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("direccion@ext.live.u-tad.es"))
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        intent.putExtra(Intent.EXTRA_TEXT, texto)
        intent.type = "message/rfc822"
        startActivity(intent)

        return true
    }
    private fun putData(){
        Glide.with(this).load(imagenDisco).into(binding.imagenDetalleDisco)
        val str= "Disco: ${nombreDisco}\nGrupo: ${grupoDisco}\nFecha de Publicacion: ${publicacionDisco}\nDiscografica: ${discografica}"
        binding.textDetalleDisco.text=str

    }


}