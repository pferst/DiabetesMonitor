package com.example.sugar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.sugar.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
//        isCurrentUser()
    }

    private fun isCurrentUser() {
      fbAuth.currentUser?.let { auth ->
          val intent = Intent(applicationContext,MainActivity::class.java).apply {
              flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
          }
          if(fbAuth.currentUser!=null)
          {
              // Zasadniczo niezbyt potrzebne bo i tak działa, ale po restarcie się trzeba znowu logować i to tego nie zmieni
          }
//          Navigation.findNavController(view)
//              .navigate(R.id.action_logIn_to_homeScreen)
//          startActivity(intent)
      }
    }
}