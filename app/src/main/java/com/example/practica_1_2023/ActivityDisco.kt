package com.example.practica_1_2023

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_1_2023.adapter.AdapterDisco
import com.example.practica_1_2023.adapter.AdapterGrupo
import com.example.practica_1_2023.databinding.ActivityDiscoBinding
import com.example.practica_1_2023.databinding.ActivityMainBinding
import com.example.practica_1_2023.dialog.dialog_new_disco
import com.example.practica_1_2023.model.disco
import com.example.practica_1_2023.model.grupo
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.prefs.Preferences
import kotlin.math.log


class ActivityDisco : AppCompatActivity(),dialog_new_disco.OnDialogNewDisco {
    private lateinit var grupoDisco: String
    private lateinit var binding:ActivityDiscoBinding
    private lateinit var discoArray: ArrayList<disco>
    private var nuevosDiscos: ArrayList<disco>
    private lateinit var adaptadorDisco: AdapterDisco
    init {
        nuevosDiscos= ArrayList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disco)
        grupoDisco = intent.extras?.getString("nombreGrupo","") ?: ""
        binding = ActivityDiscoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.grupoDisco.text=grupoDisco
        discoRecycler(grupoDisco)
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("Discos", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val arrayListAsString = gson.toJson(nuevosDiscos)
        editor.putString( "discos", arrayListAsString)
        editor.apply()

    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("Discos", Context.MODE_PRIVATE)
        val gson = Gson()
        val arrayListAsString = sharedPreferences.getString("discos", null)
        Log.v("nuevodisco",arrayListAsString.toString())
        if (arrayListAsString != null) {
            nuevosDiscos = gson.fromJson(arrayListAsString, object : TypeToken<ArrayList<disco>>() {}.type)
        }
        if(!nuevosDiscos.none()){
            nuevosDiscos.forEach{
                if(!discoArray.contains(it) && it.grupo==grupoDisco) {
                    discoArray.plusAssign(it)
                }
            }
        }
        adaptadorDisco= AdapterDisco(discoArray,applicationContext)
        binding.recyclerDiscos.adapter= adaptadorDisco
        binding.recyclerDiscos.layoutManager= LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)
    }

    fun discoRecycler(nombreGrupo:String){
        discoArray = ArrayList()
        selectGroup(nombreGrupo,discoArray)
        adaptadorDisco= AdapterDisco(discoArray,applicationContext)
        binding.recyclerDiscos.adapter= adaptadorDisco
        binding.recyclerDiscos.layoutManager= LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val dialogoPerso = dialog_new_disco()
        dialogoPerso.show(supportFragmentManager,null)
        return true
    }


    private fun selectGroup(nombreGrupo:String,discoArray:ArrayList<disco>) {
        when (nombreGrupo) {
            "Guns n' Roses" ->  gunRoses(discoArray)
            "Scorpions" ->      scorpions(discoArray)
            "Dio"->             dio(discoArray)
            "Papa Roach"->      papa(discoArray)
            "Green Day"->       green(discoArray)
            "Radiohead"->       radio(discoArray)
            "Pink Floyd"->      pink(discoArray)
        }
    }
    private fun gunRoses(discoArray:ArrayList<disco>){
        discoArray.add(disco("1987","Appetite For Destruction","Guns n' Roses","Geffen Records","https://i.discogs.com/VQy0HOhX4RiGLIg0LXi-RiZMJG8wtJDvFaBbarerjyA/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM4Mzc3/Ny0xMTczNTQzMzU5/LmpwZWc.jpeg"))
        discoArray.add(disco("1991","Use Your Illusion I","Guns n' Roses","Geffen Records","https://i.discogs.com/dSQalpljDv4OiJre2CVQUT3nn7ctYnbU7GKq0a_FYJQ/rs:fit/g:sm/q:90/h:590/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIyMDY3/ODAtMTU2MDM1ODc4/My05Nzg4LmpwZWc.jpeg"))
        discoArray.add(disco("1991","Use Your Illusion II","Guns n' Roses","Geffen Records","https://i.discogs.com/OngNbX9bF408l6DVLbJ6KiArlMNgH56_o9w4wT0nGHg/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIwNDgz/NTItMTQyODkzNzUy/OS02NzkzLnBuZw.jpeg"))
        discoArray.add(disco("1993","The Spaghetti Incident?","Guns n' Roses","Geffen Records","https://i.discogs.com/IPYh_z_cUY6nSW_9qmnnhfCVW9AWcZVr1_yG3K3secY/rs:fit/g:sm/q:90/h:597/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTcyNTQ0/NjctMTQzODU1MDQ1/Ny04MDI2LmpwZWc.jpeg"))
        discoArray.add(disco("2008","Chinese Democracy","Guns n' Roses","Geffen Records","https://i.discogs.com/DzOuQ6b2tMpH2mlWbUQfNvJRwgl-mTW8J0UZLtrHnVw/rs:fit/g:sm/q:90/h:595/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE1NDYz/MDAtMTQ1OTQ5ODcz/Mi0yMTI0LmpwZWc.jpeg"))
    }
    private fun scorpions(discoArray:ArrayList<disco>){
      discoArray.add(disco("1972","Lonesome Crow","Scorpions","Brain, Metronome","https://i.discogs.com/_tMkROzxJgMKK2uRoogukGoNaE5xxF0Jma9srxhEZ2U/rs:fit/g:sm/q:90/h:590/w:594/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTgyNDcw/OC0xNDUyNTgxODgz/LTM4MjUuanBlZw.jpeg"))
        discoArray.add(disco("1974","Fly To The Rainbow","Scorpions","RCA Victor","https://i.discogs.com/epGkDdwuOcIQz7sgveEPplASHPn2h85-14ln5GJYHpk/rs:fit/g:sm/q:90/h:590/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTk1ODg5/NzgtMTY0MDY5MTg2/MS0yMTE4LmpwZWc.jpeg"))
        discoArray.add(disco("1982","Blackout","Scorpions","Harvest, EMI Electrola","https://i.discogs.com/9OWHvI73xD5cUVjUEJ_fa9aFLOnv_6A8Ttxo_JERXJA/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQ1ODE3/NS0xNTEyMTM3Nzgw/LTI0OTUuanBlZw.jpeg"))
      discoArray.add(disco("2000","Moment Of Glory","Scorpions","EMI","https://i.discogs.com/goZJXDprWXGGTNNECxRwaT1a-jW0vTiynxN6iG63iyo/rs:fit/g:sm/q:90/h:600/w:587/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQ0NzU2/NDUtMTQ5MTk1MDk3/OS05MzkzLmpwZWc.jpeg"))
      discoArray.add(disco("2001","Acoustica","Scorpions","EastWest","https://i.discogs.com/cUEwIgv4O6MyALVjB1OS0TFDMSaKjng3RluM4SI8NGY/rs:fit/g:sm/q:90/h:599/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE0MDY3/MzgtMTY3OTgzMDI0/OS04OTYyLmpwZWc.jpeg"))
    }
    private fun dio(discoArray:ArrayList<disco>){
        discoArray.add(disco("1983","Holy Diver","Dio","Warner Bros. Records","https://i.discogs.com/Tebkvc5nA9AyMVaxBHgLRnVQFEjP6HZEe81vCrjZGdA/rs:fit/g:sm/q:90/h:598/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEzMzE2/Mzg1LTE2NDg1Mzg3/MTctNTcyMC5qcGVn.jpeg"))
        discoArray.add(disco("1984","The Last In Line","Dio","Vertigo","https://i.discogs.com/tVs4OwDQBFoO8hW3S_VxMmX5m1LbmE-A21mcsYvzxxE/rs:fit/g:sm/q:90/h:506/w:510/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIxNDUw/ODEtMTI4NDIxNjIx/Ni5qcGVn.jpeg"))
        discoArray.add(disco("1986","Intermission","Dio","Vertigo","https://i.discogs.com/a9KIsCkJKMW4EIaILEdDrqsH0ezUjHhRr7eZB26YC2M/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEyMjQ1/NzctMTQzNjA5NjYy/NS03NjI5LmpwZWc.jpeg"))
        discoArray.add(disco("1993","Strange Highways","Dio","Vertigo","https://i.discogs.com/baS7IcgCYrtyD-zkMWib5UX1pjHj7atPhW9TCvE7ZTs/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEyNzQ3/NDUtMTI5ODQ1NTA3/Mi5qcGVn.jpeg"))
        discoArray.add(disco("2000","Magica","Dio","Spitfire Records","https://i.discogs.com/lH3BtLWE7wcnSa7SlXFsSez12uoFqfF4H2djX_AV60M/rs:fit/g:sm/q:90/h:200/w:200/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE3MzY5/OTYtMTI0MDExMTQ0/Mi5qcGVn.jpeg"))

    }

    private fun papa(discoArray:ArrayList<disco>){
        discoArray.add(disco("2000","Infest","Papa Roach","DreamWorks Records","https://i.discogs.com/idwqJnKwHkSSBDRsmNSlrRLjhURagTALmr31kdYdWjM/rs:fit/g:sm/q:90/h:600/w:598/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQyODA0/OS0xNTAwMjU0OTI3/LTQ2MDcuanBlZw.jpeg"))
        discoArray.add(disco("2002","Lovehatetragedy","Papa Roach","DreamWorks Records","https://i.discogs.com/Qau54UmO6XfIc1mKH_EhtV4A5f_z5Y6oW6avZHMydxU/rs:fit/g:sm/q:90/h:594/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTc4Mjc4/MC0xNDEzODI3Mzg3/LTY4NjIuanBlZw.jpeg"))
        discoArray.add(disco("2009","Metamorphosis","Papa Roach","DGC Records, Interscope Records","https://i.discogs.com/DD60LwA55PFPZt5c_3fbpuaH6GFgl9jso80lgdZO9HU/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI3NTQ3/ODItMTM4NTc3Mjc3/My04MTQ4LmpwZWc.jpeg"))
        discoArray.add(disco("2012","The Connection","Papa Roach","Eleven Seven Music","https://i.discogs.com/mg8g781SpkzWnb1yMPLo-hSZFCkUKx3Uq0XuLWtPwV8/rs:fit/g:sm/q:90/h:500/w:500/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQwOTEy/MjYtMTM1NDkzNTIx/NS0zMjE5LmpwZWc.jpeg"))
        discoArray.add(disco("2017","Crooked Teeth","Papa Roach","Eleven Seven Music","https://i.discogs.com/o_gPTkYFFaFerDIMbJc6BcEg00JHECYFXEklzsIaGj0/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEwNDI5/MTk3LTE0OTcyNTU3/MTQtNDg5Ny5qcGVn.jpeg"))

    }
    private fun green(discoArray:ArrayList<disco>){
        discoArray.add(disco("1994","Dookie", "Green Day","Reprise Records","https://i.discogs.com/97ebzLVUHs_asnbIKbnPbeD_turZRhyF9Ghm0L0oWwM/rs:fit/g:sm/q:90/h:600/w:599/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIxMDM3/ODgtMTUwNzgxNDY2/Ny05NTU4LmpwZWc.jpeg"))
        discoArray.add(disco("2004","American Idiot", "Green Day","Reprise Records","https://i.discogs.com/hKIvFBg9gGKs_K89iDQqDIjpeVLug9vRTdSK1u5rUyo/rs:fit/g:sm/q:90/h:589/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTk3Mzcw/Ni0xNDg4MTYzOTE2/LTg1MTcuanBlZw.jpeg"))
        discoArray.add(disco("2009","21st Century Breakdown", "Reprise Records","Green Day","https://i.discogs.com/IGxKcJK5tElqOoovPRYEFahRvzKaaDnrnggJDxZ-wN8/rs:fit/g:sm/q:90/h:596/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE4ODMy/MDAtMTUxMzcwNzUy/Ni0yMzI3LmpwZWc.jpeg"))
        discoArray.add(disco("2012","¡Uno!", "Green Day","Reprise Records","https://i.discogs.com/opW2q1CmHexdzNWezz92f2AEWHXgyojQo4Wr4J6IrZM/rs:fit/g:sm/q:90/h:600/w:595/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM4OTI5/NDAtMTQzNjgzNTM1/OC05OTQ0LmpwZWc.jpeg"))
        discoArray.add(disco("2012","¡Dos!", "Green Day","Reprise Records","https://i.discogs.com/M0fmjkJLXeGH8wS6m-493EVY3U6PGgoPwXXyrjCoeMY/rs:fit/g:sm/q:90/h:467/w:476/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQwMzE2/MTgtMTM1Mjk1NTY0/NS00OTA0LmpwZWc.jpeg"))
        discoArray.add(disco("2012","¡TRÉ!", "Green Day","Reprise Records","https://i.discogs.com/WrdXAv2m_eqlXcxRcMpTqL6sG5VEv4B9Os3XQetwUC8/rs:fit/g:sm/q:90/h:465/w:469/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQxMDQ1/NTctMTM1NTM2ODAw/Mi04NTA3LmpwZWc.jpeg"))

    }

    private fun radio(discoArray:ArrayList<disco>){
        discoArray.add(disco("1994","The Bends","Radiohead","Parlophone,","https://i.discogs.com/i6MXaXRBfHBSPzqpEXlpts2oGlGYFUaoZDW-OFPVBo4/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM2ODEx/Ni0xNTg4ODgzOTM2/LTgyNDcuanBlZw.jpeg"))
        discoArray.add(disco("1997","OK Computer","Radiohead","Parlophone","https://i.discogs.com/F_KSyKjGi2YN5SBttMhdgP2zyNdmHv7HHWvDVGj3Shg/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQ5NTA3/OTgtMTM4ODYyMzYx/MS0yMzYyLmpwZWc.jpeg"))
        discoArray.add(disco("2001","Amnesiac","Radiohead","Parlophone","https://i.discogs.com/yHRIDUJ6o02jXnZneAyngGLuW_SWH133z5oED0c2__4/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTI2NzEz/My0xMjQ2OTE3NDM3/LmpwZWc.jpeg"))
        discoArray.add(disco("2003","Not My Fault.","Radiohead","Capitol Records","https://i.discogs.com/KZJHd2hzuKg3SGFineh_cM40mEIBCy_O28pkI2c1dcc/rs:fit/g:sm/q:90/h:590/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIyNjIw/MjQtMTI3NjI3OTg5/Ny5qcGVn.jpeg"))
        discoArray.add(disco("2007","In Rainbows","Radiohead","XL Recordings","https://i.discogs.com/M_QmofVfPwV_7Rc6z17_FqKtnvo26WMVF2_TMjBLQ_E/rs:fit/g:sm/q:90/h:600/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTExNzQy/OTYtMTM2MzQzNDA3/My0yMjY1LmpwZWc.jpeg"))


    }
    private fun pink(discoArray:ArrayList<disco>){
        discoArray.add(disco("1971","Meddle","Pink Floyd","Harvest","https://i.discogs.com/nlcHURJ-xZgDIezGAne4lRoiOZJYbb-qHxf7NMfwTQw/rs:fit/g:sm/q:90/h:592/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTQxMzc0/OTMtMTUzNzIxNjkz/NS05NzUzLmpwZWc.jpeg"))
        discoArray.add(disco("1971","Atom Heart Mother","Pink Floyd","Harvest","https://i.discogs.com/_KjmSHmMOX6D9mXaircr0HJrEjd2NraEhPOP71nZ5CY/rs:fit/g:sm/q:90/h:596/w:598/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM3Mjc4/MC0xMjAyMDMxNzkw/LmpwZWc.jpeg"))
        discoArray.add(disco("1973","The Dark Side Of The Moon","Pink Floyd","Harvest","https://i.discogs.com/9TFRqx1yW7EkC71l42QR2UEjnSWzcXT5TGDsfWxT4xE/rs:fit/g:sm/q:90/h:600/w:597/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTE4NzMw/MTMtMTY2NzIwODk0/MC01NTc3LmpwZWc.jpeg"))
        discoArray.add(disco("1977","Animals","Pink Floyd","Harvest","https://i.discogs.com/4Hxf3HdivnSYH-o7N3Ri4D3kcrBupW1ZZTbAHaYNmnc/rs:fit/g:sm/q:90/h:600/w:592/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTM5MTMy/Mi0xNjQ4NDkwODk1/LTI2NzkuanBlZw.jpeg"))
        discoArray.add(disco("1979","The Wall","Pink Floyd","Harvest","https://i.discogs.com/L_VyYyJq5qz9dKeMRuRSGg3ftbl-sadWu4oaJotdn-M/rs:fit/g:sm/q:90/h:600/w:588/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIwNDE2/MTgtMTM5MDY3OTAy/NS0yMzcyLmpwZWc.jpeg"))
        discoArray.add(disco("1994","The Division Bell","Pink Floyd","EMI United Kingdom","https://i.discogs.com/JvXpAAERE19zXnCA7O4LqeLSFhpRaezgkNspxAB_nik/rs:fit/g:sm/q:90/h:596/w:600/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTEyOTAx/MDMtMTQ1NDcyMjgw/Mi00Njc3LmpwZWc.jpeg"))

    }
    override fun onDatoAdd(publicacion:String,nombreDisco:String,discografica:String,imagen:String) {
        nuevosDiscos.add(disco(publicacion,nombreDisco,grupoDisco,discografica,imagen))
    }


}