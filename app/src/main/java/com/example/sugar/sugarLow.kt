package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_sugar_high.view.*
import kotlinx.android.synthetic.main.fragment_sugar_low.view.*


class sugarLow : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sugar_low, container, false)
        view.low_button.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_sugarLow_to_homeScreen)) }

        return view
    }
}