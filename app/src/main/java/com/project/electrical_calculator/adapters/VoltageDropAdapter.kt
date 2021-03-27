package com.project.electrical_calculator.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.entities.VoltageDrop

class VoltageDropAdapter(
    private val listener: VoltageDropRowItemClickListener
) : RecyclerView.Adapter<VoltageDropAdapter.MyViewHolder>() {

    var listOfVoltageDrops = ArrayList<VoltageDrop>()

    fun setListData(data: ArrayList<VoltageDrop>) {
        this.listOfVoltageDrops = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row = layoutInflater.inflate(R.layout.activity_voltage_drop_row, parent, false)
        return MyViewHolder(row, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listOfVoltageDrops[position])
    }

    override fun getItemCount(): Int {
        return listOfVoltageDrops.size
    }

    inner class MyViewHolder(view: View, private val listener: VoltageDropRowItemClickListener) :
        RecyclerView.ViewHolder(view) {
        private val voltageDropValue: TextView = view.findViewById(R.id.txt_voltage_drop_value)
        private val btnDeleteVoltageDrop: ImageView = view.findViewById(R.id.iv_delete_voltage_drop)

        fun bind(data: VoltageDrop) {
            voltageDropValue.text = data.voltageDrop.toString()
            btnDeleteVoltageDrop.setOnClickListener {
                listener.onDeleteVoltageDropClickListener(data)
            }
        }
    }

    interface VoltageDropRowItemClickListener {
        fun onDeleteVoltageDropClickListener(voltageDrop: VoltageDrop)
    }
}