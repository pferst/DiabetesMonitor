package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_log_in.view.*

class LogIn : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)
        view.buttonLogOK.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logIn_to_homeScreen)) }
        view.goToRegistration.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logIn_to_registration)) }
        view.forgotpassword.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logIn_to_forgotPassword)) }

        return view
    }

}