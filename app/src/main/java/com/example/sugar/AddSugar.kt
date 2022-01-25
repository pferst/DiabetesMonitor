package com.example.sugar

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EdgeEffect
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_add_sugar.*
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import kotlinx.android.synthetic.main.fragment_home_screen.view.*
import kotlinx.android.synthetic.main.fragment_sugar_ok.view.*
import java.lang.NumberFormatException
import java.util.*
import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import java.text.SimpleDateFormat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore


class AddSugar : Fragment() {

    val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_sugar, container, false)
        if(view.add_button!=null)
        {
            view.add_button.setOnClickListener {
                addSugar(view)
            }
        }
        return view
    }

    private fun addSugar(view: View) {
        val propName = "measurement" // property name
        val currDate = SimpleDateFormat("dd/M/yyyy HH:mm:ss").format(Date())
        try
        {
            val stringi = view.addsugarEdittext.text.toString().trim()
            val stringi2 = stringi.toFloat()
            saveData(currDate,stringi2, view)
            //database.child("userId").child(propName).child(currDate).setValue(stringi2)
        } catch (error: NumberFormatException){
            Navigation.findNavController(view).navigate(R.id.action_addSugar_to_bladWpisywania)
        }
        return
    }

    private fun saveData(currDate: String, sugarLvl: Float, view: View){
        val currentDate = currDate.replace('/','-')
        val customObj = hashMapOf(
            currentDate to sugarLvl
        )
        val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users/${fbAuth.currentUser!!.uid}/Measure")
        database.child(currentDate).child("sugarLevel").setValue(sugarLvl)
            .addOnSuccessListener {
                when {
                    sugarLvl in 70.0..99.0 -> {
                        Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarOk)
                    }
                    sugarLvl > 99 -> {
                        Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarHigh)
                    }
                    sugarLvl < 70 -> {
                        Navigation.findNavController(view).navigate(R.id.action_addSugar_to_sugarLow)
                    }
                }
            }
        database.child(currentDate).child("workout").setValue(view.isWorkout.isChecked)
        database.child(currentDate).child("cheatDay").setValue(view.isCookies.isChecked)
    }


}