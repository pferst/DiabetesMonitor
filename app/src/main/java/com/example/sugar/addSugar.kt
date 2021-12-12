package com.example.sugar

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EdgeEffect
import android.widget.EditText
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_add_sugar.*
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import java.util.*


class addSugar : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_sugar, container, false)
        getView()?.findViewById<EditText>(R.id.addsugarEdittext)
        val wynik = addsugarEdittext.text.toString()
        if(wynik.toFloat()>0)
        {}


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}