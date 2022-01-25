package com.example.sugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_log_in_error.view.*


class LogInError : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_log_in_error, container, false)
        view.logInErrorOk.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logInError_to_logIn)) }
        return view
    }


}