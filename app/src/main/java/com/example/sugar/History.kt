package com.example.sugar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
//import android.databinding.BaseObservable;
//import android.databinding.Bindable;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_history.view.*
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener
import kotlin.math.log


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
    var sugarLvlsList : MutableList<ArrayElement> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val isChart: Switch = view.switch1
        val dataList: RecyclerView = view.dataList
        isChart.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // The toggle is enabled
                dataList.visibility = View.GONE
            } else {
                dataList.visibility = View.VISIBLE
                // The toggle is disabled
            }
        }

        database.child("Users/${fbAuth.currentUser!!.uid}/Measure")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        for (child in dataSnapshot.children) {
                            val newKey = child.key!!.replace(' ', '\n')
                            sugarLvlsList.add(
                                ArrayElement(
                                    date = child.key!!,
                                    sugarLvl = child.value!!.toString().toInt()
                                )
                            )
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        val list = sugarLvlsList.asReversed()
        val adapter = itemAdapter(list)
        dataList.layoutManager = LinearLayoutManager(requireContext())
        dataList.adapter = adapter
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