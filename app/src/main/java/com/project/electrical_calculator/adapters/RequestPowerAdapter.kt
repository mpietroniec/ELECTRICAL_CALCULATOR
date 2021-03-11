package com.project.electrical_calculator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.entities.RequestPower

class RequestPowerAdapter(private val listOfRequestPowers: List<RequestPower>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row =
            layoutInflater.inflate(R.layout.activity_balance_of_request_power_row, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = listOfRequestPowers[position].name
        holder.requestPower.text = listOfRequestPowers[position].requestPower.toString()
    }

    override fun getItemCount(): Int {
        return listOfRequestPowers.size
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nameTextView: TextView = view.findViewById(R.id.txt_object_name)
    val requestPower: TextView = view.findViewById(R.id.txt_request_power)
}