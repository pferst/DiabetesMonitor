package com.example.sugar

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_hisotry_list.view.*

class itemAdapter(private val dataSet: List<ArrayElement>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {

    private var database: DatabaseReference = Firebase.database.reference
    val fbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hisotry_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle?.text = dataSet[position].sugarLvl.toString()+" [mg/dl]"
        holder.cardDate?.text = dataSet[position].date
        holder.cardCookie?.isVisible = dataSet[position].cookie
        holder.cardSport?.isVisible = dataSet[position].workout
        when {
            dataSet[position].sugarLvl in 70..99 -> {
                holder.cardTitle?.setTextColor(Color.parseColor("#00aa00"))
            }
            dataSet[position].sugarLvl > 99 -> {
                holder.cardTitle?.setTextColor(Color.parseColor("#ff0000"))

            }
            dataSet[position].sugarLvl < 70 -> {
                holder.cardTitle?.setTextColor(Color.parseColor("#d88f1a"))
            }
        }

        holder.cardDelete.setOnClickListener {
            deleteMeasure(dataSet[position].date, position)
        }
    }
    fun deleteMeasure(key: String, pos: Int) {
        val newKey = key.replace('\n',' ')
        database.child("Users/${fbAuth.currentUser!!.uid}/Measure/${newKey}").removeValue().addOnSuccessListener {
            val sth : History

        }
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView? = itemView.sugarLvl
        val cardDate: TextView? = itemView.dateView
        val cardCookie: ImageView? = itemView.cheat
        val cardSport: ImageView? = itemView.sport
        val cardDelete : ImageButton = itemView.btnDelete
    }
}