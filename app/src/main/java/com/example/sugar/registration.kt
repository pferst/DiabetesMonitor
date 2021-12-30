package com.example.sugar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*

class registration : Fragment() {
    private val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =inflater.inflate(R.layout.fragment_registration, container, false)
        //view.confirmRegistration.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_registration_to_homeScreen) }

        view.confirmRegistration.setOnClickListener {
            setupRegistration(view) }

        return view
    }

    private fun setupRegistration(view: View) {
        runCatching{
            val email: String = registarion_email.text?.trim().toString()
            val password: String = password_registaration.text?.trim().toString()
            val password2d: String = password_registaration_2nd.text?.trim().toString()

            if(password==password2d){
                fbAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authRes ->
                        if(authRes.user != null)
                        {
                            val intent = Intent(requireContext(), MainActivity::class.java).apply {
                                flags =
                                    (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            }

                            startActivity(intent)
                        }else
                        { Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second)}

                    }
                    .addOnFailureListener{

                    }
            }else{
                Navigation.findNavController(view).navigate((R.id.action_registration_to_registration_error))
            }
        }.onFailure { Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second) }
        }



}