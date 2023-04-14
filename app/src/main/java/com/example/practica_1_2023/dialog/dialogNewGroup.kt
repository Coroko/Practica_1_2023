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
import com.example.practica_1_2023.R

class dialogNewGroup: DialogFragment() {

    private lateinit var vista: View
    private lateinit var editNombreGrupo: EditText
    private lateinit var editGeneroGrupo: EditText
    private lateinit var editImagenGrupo: EditText
    private lateinit var addButton: Button
    private lateinit var listener: OnDialogNewGroup

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDialogNewGroup
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        vista = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_new_group, null)
        builder.setView(vista)
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        editNombreGrupo = vista.findViewById(R.id.editNombreGrupo)
        editGeneroGrupo = vista.findViewById(R.id.editGeneroGrupo)
        editImagenGrupo = vista.findViewById(R.id.editImagenGrupo)
        addButton = vista.findViewById(R.id.addButtonGrupo)

        addButton.setOnClickListener {
            val nombreGrupo = editNombreGrupo.text.toString()
            val imagenGrupo = editImagenGrupo.text.toString()
            val generoGrupo = editGeneroGrupo.text.toString()

            fun checkdata(): Boolean {
                var flag = 0
                if (editNombreGrupo.text.isEmpty()) {
                    editNombreGrupo.error = "This field is required"
                    flag = 1
                }
                if (editGeneroGrupo.text.isEmpty()) {
                    editGeneroGrupo.error = "This field is required"
                    flag = 1
                }
                if (editImagenGrupo.text.isEmpty()) {
                    editImagenGrupo.error = "This field is required"
                    flag = 1
                }
                return flag == 0

            }

            if (checkdata()) {
                listener.onDataAddGroup(nombreGrupo, generoGrupo, imagenGrupo)
                dismiss()
            }
        }

    }

    interface OnDialogNewGroup {
        fun onDataAddGroup(
          nombreGrupo:String,
          generoGrupo:String,
          imagenGrupo:String

        )
    }
}