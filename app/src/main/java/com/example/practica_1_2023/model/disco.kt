package com.example.practica_1_2023.model


data class disco(var publicacion:String,var nombre: String,var grupo: String,var imagen: String){

    var discografica: String?= ""

    constructor(publicacion: String, nombre: String,grupo: String,discografica: String,imagen: String,
               ):this(publicacion,nombre,grupo,imagen){
        this.discografica=discografica
    }


}


