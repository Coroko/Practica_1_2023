package com.example.practica_1_2023.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import  com.example.practica_1_2023.R

class dialog_new_disco: DialogFragment() {

    private lateinit var vista: View
    private lateinit var editPublicacion: EditText
    private lateinit var editNombreDisco: EditText
    private lateinit var editImagen: EditText
    private lateinit var editDiscografica: EditText
    private lateinit var addButton: Button
    private lateinit var listener: OnDialogNewDisco

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDialogNewDisco
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        vista = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_new_disco,null)
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editDiscografica = vista.findViewById(R.id.editDiscografica)
        editImagen = vista.findViewById(R.id.editImagen)
        editNombreDisco= vista.findViewById(R.id.editNombreDisco)
        editPublicacion= vista.findViewById(R.id.editPublicacion)
        addButton = vista.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val discografica=editDiscografica.text.toString()
            val imagen=editImagen.text.toString()
            val nombreDisco=editNombreDisco.text.toString()
            val publicacion=editPublicacion.text.toString()

            fun checkdata():Boolean{
                var flag=0
                if (editDiscografica.text.isEmpty()) {
                    editDiscografica.error = "This field is required"
                    flag=1
                }
                if (editImagen.text.isEmpty()) {
                    editImagen.error = "This field is required"
                    flag=1
                }
                if (editNombreDisco.text.isEmpty()) {
                    editNombreDisco.error = "This field is required"
                    flag=1
                }
                if (editPublicacion.text.isEmpty()) {
                    editPublicacion.error = "This field is required"
                    flag=1
                }
                return flag==0

            }

            if(checkdata()){
                listener.onDatoAdd(publicacion,nombreDisco,discografica,imagen)
                dismiss()
            }
        }

    }
    interface OnDialogNewDisco{
        fun onDatoAdd(publicacion:String,nombreDisco:String,discografica:String,imagen:String)
    }
}