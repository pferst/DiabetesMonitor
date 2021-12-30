package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_registration_error_second.view.*

class registration_error_second : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration_error_second, container, false)
        view.registrationErrorOk_2nd.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_registration_error_second_to_registration) }
        return view
    }

}