package com.example.practica_1_2023

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.practica_1_2023.adapter.AdapterGrupo
import com.example.practica_1_2023.databinding.ActivityMainBinding
import com.example.practica_1_2023.dialog.dialogNewGroup
import com.example.practica_1_2023.dialog.dialog_new_disco
import com.example.practica_1_2023.model.disco
import com.example.practica_1_2023.model.grupo
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(),dialogNewGroup.OnDialogNewGroup {
    lateinit var binding: ActivityMainBinding
    private lateinit var listaGrupos: ArrayList<grupo>
    private lateinit var adaptadorGrupos: AdapterGrupo
    private var nuevosGrupos: ArrayList<grupo>
    init {
        nuevosGrupos= ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        gruposRecycler()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_info,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.info_menu-> Snackbar.make(
                binding.root,
                "Apliacion creada por Javier Cámara Jabonero\nU-tad 2023 INSO DAM",
                Snackbar.LENGTH_SHORT
            ).show()
            R.id.add_grupo-> addGrupo()
        }
        return true
    }
    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("Grupos", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val arrayListAsString = gson.toJson(nuevosGrupos)
        editor.putString( "grupos", arrayListAsString)
        editor.apply()

    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("Grupos", Context.MODE_PRIVATE)
        val gson = Gson()
        val arrayListAsString = sharedPreferences.getString("grupos", null)
        if (arrayListAsString != null) {
            nuevosGrupos = gson.fromJson(arrayListAsString, object : TypeToken<ArrayList<grupo>>() {}.type)
        }
        Log.v("mine",nuevosGrupos.toString())
        Log.v("mine2",listaGrupos.toString())
        if(!nuevosGrupos.none()){
            nuevosGrupos.forEach {
                Log.v("mine3",it.toString())
                if(!listaGrupos.contains(it)) {
                    listaGrupos.plusAssign(it)
                }
            }
        }
        adaptadorGrupos= AdapterGrupo(listaGrupos,applicationContext)
        binding.recyclerGrupos.adapter= adaptadorGrupos
        binding.recyclerGrupos.layoutManager= LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)
    }



    private fun addGrupo(){
        val diaNewGrupo = dialogNewGroup()
        diaNewGrupo.show(supportFragmentManager,null)
    }


    private fun gruposRecycler(){
        listaGrupos= ArrayList()
        listaGrupos.plusAssign(grupo("Guns n' Roses","https://imgs.search.brave.com/zuCxosu1iwqKdZQRT0X2LrWrbB13Bsp8dajAcX6TH_o/rs:fit:150:150:1/g:ce/aHR0cDovL3d3dy5z/b25pY2V4Y2Vzcy5j/b20vZ3Vucy1uLXJv/c2VzLWxvZ28tNTIw/MDExOS0xNTB4MTUw/LnBuZw","Rock"))
        listaGrupos.plusAssign(grupo("Scorpions" ,"https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fassets.stickpng.com%2Fthumbs%2F585ab2494f6ae202fedf2922.png&f=1&nofb=1&ipt=52fd6f0d35ca8d7c43494ff6317d4dc862301e29331fcc1c044c298bec0d2ee1&ipo=images","Rock"))
        listaGrupos.plusAssign(grupo("Dio","https://imgs.search.brave.com/5yH29eqKglsR3_c3iwot74jx9YuzT9uXPWIbPO8r8Sc/rs:fit:190:190:1/g:ce/aHR0cHM6Ly90b3Bw/bmcuY29tL3VwbG9h/ZHMvdGh1bWJuYWls/L2Rpby1sb2dvLXZl/Y3Rvci1mcmVlLWRv/d25sb2FkLTExNTc0/MTMxNTA0YjN4ZmRm/N2Fpcy5wbmc","Rock"))
        listaGrupos.plusAssign(grupo("Papa Roach","https://imgs.search.brave.com/F5B_IMm562K2mC_RjOIhHTunhRsH1j1V_Jh5y6cXhfc/rs:fit:360:203:1/g:ce/aHR0cHM6Ly8xMDAw/bWFyY2FzLm5ldC93/cC1jb250ZW50L3Vw/bG9hZHMvMjAyMC8w/My9QYXBhLVJvYWNo/LUxvZ28tMzYweDIw/My5wbmc","Rock"))
        listaGrupos.plusAssign(grupo("Green Day","https://imgs.search.brave.com/rGOV5_o_MxbS7aEicwAKWENw_5ADijG7dERJ-AEUvrw/rs:fit:320:180:1/g:ce/aHR0cHM6Ly8xMDAw/bWFyY2FzLm5ldC93/cC1jb250ZW50L3Vw/bG9hZHMvMjAyMC8w/MS9HcmVlbi1EYXkt/TG9nby0zMjB4MTgw/LnBuZw","Punk"))
        listaGrupos.plusAssign(grupo("Radiohead","https://imgs.search.brave.com/g81kkkn1mCx62Vr9ohPHxyc3bU5trofIioRP6mC1Vys/rs:fit:320:200:1/g:ce/aHR0cHM6Ly8xMDAw/bG9nb3MubmV0L3dw/LWNvbnRlbnQvdXBs/b2Fkcy8yMDIwLzA0/L1JhZGlvaGVhZC1M/b2dvLTMyMHgyMDAu/cG5n","Alt Rock"))
        listaGrupos.plusAssign(grupo("Pink Floyd","https://imgs.search.brave.com/mzrQfeDWX0ajKNwM_nIJoWJdT6LhjLbgdo42UXOAvAA/rs:fit:474:266:1/g:ce/aHR0cHM6Ly9sb2dv/cy13b3JsZC5uZXQv/d3AtY29udGVudC91/cGxvYWRzLzIwMjAv/MTEvUGluay1GbG95/ZC1Mb2dvLnBuZw","Rock prog"))


        adaptadorGrupos= AdapterGrupo(listaGrupos,applicationContext)
        binding.recyclerGrupos.adapter= adaptadorGrupos
        binding.recyclerGrupos.layoutManager= LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)
    }

    override fun onDataAddGroup(nombreGrupo: String, generoGrupo: String, imagenGrupo: String) {
        nuevosGrupos.plusAssign(grupo(nombreGrupo,imagenGrupo,generoGrupo))
    }

}