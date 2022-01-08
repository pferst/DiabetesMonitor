package com.example.sugar

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
//tests
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
//
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import java.util.logging.Logger


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
//        runCatching{
//            val email: String = registarion_email.text?.trim().toString()
//            val password: String = password_registaration.text?.trim().toString()
//            val password2d: String = password_registaration_2nd.text?.trim().toString()
//
//            if(password==password2d){
//                fbAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnSuccessListener { authRes ->
//                        if(authRes.user != null)
//                        {
//                            val intent = Intent(requireContext(), MainActivity::class.java).apply {
//                                flags =
//                                    (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                            }
//
//                            startActivity(intent)
//                        }else
//                        { Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second)}
//
//                    }
//                    .addOnFailureListener{
//
//                    }
//            }else{
//                Navigation.findNavController(view).navigate((R.id.action_registration_to_registration_error))
//            }
//        }.onFailure { Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second) }
        when {
            TextUtils.isEmpty(registarion_email.text.toString().trim { it <= ' '}) -> {
                Log.d("Incorrect Passwords", "Some shit 1")
                Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second)
            }
            TextUtils.isEmpty(password_registaration.text.toString().trim { it <= ' '}) -> {
                Log.d("Incorrect Passwords", "Some shit 2")
                Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second)
            }
            TextUtils.isEmpty(password_registaration_2nd.text.toString().trim { it <= ' '}) -> {
                Log.d("Incorrect Passwords", "Some shit 3")
                Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error_second)
            }
            else -> {
                Log.d("Incorrect Passwords", "Some shit 4")
                //getting email and password, no one of them are empty
                val email: String = registarion_email.text.toString().trim{ it <= ' '}
                val password: String = password_registaration.text.toString().trim{ it <= ' '}
                val password2d: String = password_registaration_2nd.text.toString().trim{ it <= ' '}
                if(password != password2d)
                {
                    Log.d("Incorrect Passwords", "Some shit 5")
                    Navigation.findNavController(view).navigate(R.id.action_registration_to_registration_error)
                }
                else {
                    Log.d("Incorrect Passwords", "Some shit 6")
                    fbAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    Log.d("Incorrect Passwords", "Some shit 7")
                                    val fbUser: FirebaseUser = task.result!!.user!!
//
                                    val intent = Intent(requireContext(), MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", fbUser.uid)
                                    intent.putExtra("email_id", email)
//                                    startActivity(intent)
                                    Navigation.findNavController(view).navigate(R.id.action_registration_to_homeScreen)
//                                    finish()
                                } else {
                                    Log.d("Incorrect Passwords", "Some shit 8")
                                    Navigation.findNavController(view)
                                        .navigate(R.id.action_registration_to_registration_error_second)
                                }
                            }
                        )
                }
            }
        }
    }



}