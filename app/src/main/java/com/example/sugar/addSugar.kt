package com.example.sugar

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.Toast
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
        val shit =getView()?.findViewById<EditText>(R.id.addsugarEdittext)

        getView()?.findViewById<Button>(R.id.add_button)?.setOnClickListener() {
            val inputValue:String = addsugarEdittext.text.toString()

                textView2.setText(inputValue).toString()

        }


        //view.add_button.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarOk) }


        /*getView()?.findViewById<Button>(R.id.add_button)?.setOnClickListener {
            var string = addsugarEdittext.text.toString()
            if(string.toFloat()>5)
            {
                view.add_button.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarOk) }
            }
            else{
                view.add_button.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarHigh) }
            }
        }*/



        return view
        }





}