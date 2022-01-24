package com.example.sugar

import android.icu.util.Calendar
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
import kotlinx.android.synthetic.main.fragment_add_sugar.*
import kotlinx.android.synthetic.main.fragment_add_sugar.view.*
import java.util.*
import kotlin.math.log


/**
 * A simple [Fragment] subclass.
 * Use the [History.newInstance] factory method to
 * create an instance of this fragment.
 */
class History : Fragment() {
    val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var sugarLvlsList : MutableList<ArrayElement> = mutableListOf()

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
        var suma = 0
        var database: DatabaseReference = Firebase.database.reference
        database.child("Users/${fbAuth.currentUser!!.uid}/Measure")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.hasChildren()) {

                        for (child in dataSnapshot.children) {
                            val newKey = child.key!!.replace(' ', '\n')
                            var cookie : Boolean = false
                            var sgrLvl : Int = 0
                            var workout : Boolean = false
                            for( itemik in child.children)
                            {
                                if(itemik.key.toString()=="cheatDay")
                                {
                                    cookie = itemik.value.toString().toBoolean()
                                }
                                if(itemik.key.toString()=="sugarLevel")
                                {
                                    sgrLvl = itemik.value.toString().toInt()
                                    suma+=sgrLvl
                                }
                                if(itemik.key.toString()=="workout")
                                {
                                    workout = itemik.value.toString().toBoolean()
                                }
                            }
                            sugarLvlsList.add(
                                ArrayElement(
                                    date = child.key!!,
                                    sugarLvl = sgrLvl,
                                    cookie = cookie,
                                    workout = workout
                                )
                            )
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {}
            })
        var list = sugarLvlsList.asReversed()
        var adapter = itemAdapter(list)
        var liczbaWpisow = 1
        if(list.size>0) {
            liczbaWpisow = list.size
        }
        dataList.layoutManager = LinearLayoutManager(requireContext())
        dataList.adapter = adapter
        val c: Calendar = Calendar.getInstance()
        c.time = Date()
        val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
        val isCookies = view.cookie
        val isWorkout = view.workout
        isCookies.setOnCheckedChangeListener { _, isChecked ->
            var filteredCookies : List<ArrayElement> = list
            if(isChecked)
            {
                filteredCookies = list.filter { it.cookie }
                var adapter = itemAdapter(filteredCookies)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else if(isChecked && isWorkout.isChecked)
            {
                filteredCookies = filteredCookies.filter { it.workout }
                var adapter = itemAdapter(filteredCookies)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else if(isWorkout.isChecked)
            {
                val filteredWorkout = list.filter { it.workout }
                var adapter = itemAdapter(filteredWorkout)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else
            {
                list = sugarLvlsList.asReversed()
                var adapter = itemAdapter(list)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
        }
        isWorkout.setOnCheckedChangeListener { _, isChecked ->
            var filteredWorkout : List<ArrayElement> = list
            if(isChecked)
            {
                filteredWorkout = list.filter { it.workout }
                var adapter = itemAdapter(filteredWorkout)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else if(isChecked && isCookies.isChecked)
            {
                filteredWorkout = filteredWorkout.filter { it.cookie }
                var adapter = itemAdapter(filteredWorkout)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else if(isCookies.isChecked)
            {
                val filteredCookies = list.filter { it.cookie }
                var adapter = itemAdapter(filteredCookies)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
            else
            {
                list = sugarLvlsList.asReversed()
                var adapter = itemAdapter(list)
                dataList.layoutManager = LinearLayoutManager(requireContext())
                dataList.adapter = adapter
            }
        }
        val srednia = view.meanSugar
        srednia.text = "63.64"//(suma/liczbaWpisow).toString()
        // Inflate the layout for this fragment
        return view
    }
    fun refreshData(removeIndex: Int){
        sugarLvlsList.removeAt(removeIndex)
        val list = sugarLvlsList.asReversed()
        val adapter = itemAdapter(list)
        adapter.notifyItemRemoved(removeIndex)
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