package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_blad_wpisywania.view.*


class BladWpisywania : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blad_wpisywania, container, false)

        view.error_button.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_bladWpisywania_to_addSugar) }

        return view
    }

}