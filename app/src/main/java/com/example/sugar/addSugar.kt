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
import java.util.*


class addSugar : Fragment() {



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
        var stringi = view.addsugarEdittext.text.toString().trim();
        if(stringi.toFloat()>5)
        {
              Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarOk)
        }
        else{
            Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarHigh)
        }
        return;
    }




}