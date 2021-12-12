package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home_screen.view.*


class homeScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)
        view.dodajWynik.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_addSugar) }
        view.historia.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_homeScreen_to_history) }

        return view
    }


}