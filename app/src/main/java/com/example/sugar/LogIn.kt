package com.example.sugar

import android.app.ActionBar
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sugar.databinding.FragmentLogInBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_log_in.view.*
import kotlinx.android.synthetic.main.fragment_registration.*

class LogIn : Fragment() {
    private lateinit var fbAuth: FirebaseAuth
//    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_log_in, container, false)



        fbAuth = FirebaseAuth.getInstance()
//        if(fbAuth.currentUser!!.uid!=null)
//        {
//            Log.d("log adain", "maybe possible")
//
//        }
        //wywołanie logowania
        view.buttonLogOK.setOnClickListener {
            setupLoginClick(view)
        }

        view.goToRegistration.setOnClickListener {
            Navigation.findNavController(view).navigate((R.id.action_logIn_to_registration))
        }
        view.forgotpassword.setOnClickListener {
            Navigation.findNavController(view).navigate((R.id.action_logIn_to_forgotPassword))
        }
        return view
    }

    private fun setupLoginClick(view: View) {
        //logowanie
//        runCatching  {
//            val email: String = emailLogIn.text?.trim().toString()
//            val password: String = passwordLogIn.text?.trim().toString()
//            fbAuth.signInWithEmailAndPassword(email, password)
//                .addOnSuccessListener { authRes ->
//                    if (authRes.user != null) {
//
//                        val intent = Intent(requireContext(), MainActivity::class.java).apply {
//                            flags =
//                                (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                        }
//
//                        startActivity(intent)
//                    }
//                }
//                .addOnFailureListener {
//                   // Navigation.findNavController(view).navigate((R.id.action_logIn_to_logInError))
//                }
//        }.onFailure { Navigation.findNavController(view).navigate((R.id.action_logIn_to_logInError)) }
        when {
            TextUtils.isEmpty(emailLogIn.text.toString().trim { it <= ' ' }) -> {
                Log.d("Incorrect Passwords", "Some shit 1")
                Navigation.findNavController(view)
                    .navigate(R.id.action_logIn_to_logInError)
            }
            TextUtils.isEmpty(passwordLogIn.text.toString().trim { it <= ' ' }) -> {
                Log.d("Incorrect Passwords", "Some shit 2")
                Navigation.findNavController(view)
                    .navigate(R.id.action_logIn_to_logInError)
            }
            else -> {
                Log.d("Incorrect Passwords", "Some shit 4")
                //getting email and password, no one of them are empty
                val email: String = emailLogIn.text.toString().trim { it <= ' ' }
                val password: String = passwordLogIn.text.toString().trim { it <= ' ' }
                Log.d("Incorrect Passwords", "Some shit 6")
                fbAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->
                            if (task.isSuccessful) {
//
                                val intent = Intent(requireContext(), homeScreen::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.apply {
                                    intent.putExtra("user_id", fbAuth.currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                }
//                                startActivity(intent)
                                Navigation.findNavController(view)
                                    .navigate(R.id.action_logIn_to_homeScreen)
//                                    finish()
                            } else {
                                Log.d("Incorrect Passwords", "Some shit 8")
                                Navigation.findNavController(view)
                                    .navigate(R.id.action_logIn_to_logInError)
                            }
                        }
                    )
            }
        }
    }

}

