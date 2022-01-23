package com.example.sugar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Switch
import android.widget.ToggleButton
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
//import android.databinding.BaseObservable;
//import android.databinding.Bindable;
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [History.newInstance] factory method to
 * create an instance of this fragment.
 */
class History : Fragment() {
    private var database: DatabaseReference = Firebase.database.reference
    val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val dateRangePicker =
        MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select dates")
            .build()
//    dateRangePicker.show()
//    val isChart: String? by liveData.observeAsState()
    val gone: String = "gone";
    val visible: String = "visible";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database.child("Users/${fbAuth.currentUser!!.uid}/Measure").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val isChart: Switch = view.switch1
        isChart.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                val dataList: ScrollView = view.dataListScroll
                dataList.visibility = View.GONE
            } else {
                val dataList: ScrollView = view.dataListScroll
                dataList.visibility = View.VISIBLE
                // The toggle is disabled
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment History.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            History().apply {
                arguments = Bundle().apply {
                }
            }
    }
}