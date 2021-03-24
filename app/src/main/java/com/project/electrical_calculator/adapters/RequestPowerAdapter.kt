package com.project.electrical_calculator.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.electricBalance.Constants
import com.project.electrical_calculator.electricBalance.UpdateBalanceOfRequestPower
import com.project.electrical_calculator.entities.RequestPower

class RequestPowerAdapter(
    val listener: RowItemClickListener
) : RecyclerView.Adapter<RequestPowerAdapter.MyViewHolder>() {

    var listOfRequestPowers = ArrayList<RequestPower>()

    fun setListData(data: ArrayList<RequestPower>) {
        this.listOfRequestPowers = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row =
            layoutInflater.inflate(R.layout.activity_balance_of_request_power_row, parent, false)
        return MyViewHolder(row, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.reqPowerDetails.setOnClickListener { v ->
            val item = v.tag as? RequestPower
            val context = holder.reqPowerDetails.context
            val intent = Intent(context, UpdateBalanceOfRequestPower::class.java).apply {
                putExtra(Constants.DATA_RECORD_ID, item?.id)
            }
            context.startActivity(intent)
        }
        holder.bind(listOfRequestPowers[position])
    }

    override fun getItemCount(): Int {
        return listOfRequestPowers.size
    }

    inner class MyViewHolder(view: View, val listener: RowItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.txt_object_name)
        val reqPower: TextView = view.findViewById(R.id.txt_request_power)
        val reqPowerDetails: TextView = view.findViewById(R.id.txt_request_power_details)
        val btnDelete: ImageView = view.findViewById(R.id.deleteRequestPower)

        fun bind(data: RequestPower) {
            nameTextView.text = data.name
            reqPower.text = data.power.toString()
            btnDelete.setOnClickListener {
                listener.onDeleteRequestClickListener(data)
            }
        }
    }

    interface RowItemClickListener {
        fun onDeleteRequestClickListener(requestPower: RequestPower)
        //   fun onItemClickListener(requestPower: RequestPower)
    }

}