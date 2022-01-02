package com.example.sugar

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_sugar.*
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import kotlinx.android.synthetic.main.fragment_home_screen.view.*
import kotlinx.android.synthetic.main.fragment_sugar_ok.view.*
import java.lang.NumberFormatException
import java.util.*
import android.app.AlertDialog
import android.content.DialogInterface


class AddSugar : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_sugar, container, false)
        if(view.add_button!=null)
        {
            view.add_button.setOnClickListener {
                addSugar(view)
            }
        }
        return view
    }
    private fun addSugar(view: View) {
        try
        {
            val stringi = view.addsugarEdittext.text.toString().trim()
            val stringi2 = stringi.toFloat()
            if (stringi2 in 70.0..99.0) {
                Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarOk)
            }
            if (stringi2> 99) {
                Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarHigh)
            }
            if (stringi2 < 70) {
                Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarLow)
            }
        } catch (error: NumberFormatException){
            Navigation.findNavController(view).navigate(R.id.action_addSugar_to_bladWpisywania)
            // build alert dialog
//            val dialogBuilder = AlertDialog.Builder(context)
//
//            // set message of alert dialog
//            dialogBuilder.setMessage("Podaj liczbę!")
//                // if the dialog is cancelable
//                .setCancelable(false)
//                // positive button text and action
//                .setPositiveButton("OK", DialogInterface.OnClickListener {
//                        dialog, id -> null
//                })
//
//            // create dialog box
//            val alert = dialogBuilder.create()
//            // set title for alert dialog box
//            alert.setTitle("Błędne dane")
//            // show alert dialog
//            alert.show()
        }
        return
    }




}