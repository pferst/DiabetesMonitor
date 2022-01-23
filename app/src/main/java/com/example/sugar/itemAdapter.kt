package com.example.sugar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_hisotry_list.view.*

class itemAdapter(private val dataSet: List<ArrayElement>) : RecyclerView.Adapter<itemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hisotry_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle?.text = dataSet[position].sugarLvl.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardTitle: TextView? = itemView.sugarLvl

    }
}