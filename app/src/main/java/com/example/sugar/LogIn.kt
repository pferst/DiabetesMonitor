package com.example.sugar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*

class LogIn : Fragment() {
    private val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)

        //wywołanie logowania
        view.buttonLogOK.setOnClickListener {
                setupLoginClick(view) }

        view.goToRegistration.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logIn_to_registration)) }
        view.forgotpassword.setOnClickListener { Navigation.findNavController(view).navigate((R.id.action_logIn_to_forgotPassword)) }
        return view
    }


    private fun setupLoginClick(view: View) {
        //logowanie
        runCatching  {
            val email: String = emailLogIn.text?.trim().toString()
            val password: String = passwordLogIn.text?.trim().toString()
            fbAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authRes ->
                    if (authRes.user != null) {

                        val intent = Intent(requireContext(), MainActivity::class.java).apply {
                            flags =
                                (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        }

                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                   // Navigation.findNavController(view).navigate((R.id.action_logIn_to_logInError))
                }
        }.onFailure { Navigation.findNavController(view).navigate((R.id.action_logIn_to_logInError)) }
        }

    }

