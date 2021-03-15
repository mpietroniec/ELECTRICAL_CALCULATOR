package com.project.electrical_calculator.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.electricBalance.UpdateBalanceOfRequestPower
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.viewModels.RequestPowerViewModel

var viewModel: RequestPowerViewModel? = null


class RequestPowerAdapter(
    private val listOfRequestPowers: List<RequestPower>,
    // private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<RequestPowerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val row =
            layoutInflater.inflate(R.layout.activity_balance_of_request_power_row, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.btnDelete.setOnClickListener(View.OnClickListener {
//            if (viewModel != null) {
//                viewModel?.deleteRequestPower(listOfRequestPowers[position])
//            }
//        })
        holder.btnDelete.setOnClickListener {

            viewModel?.deleteRequestPower(listOfRequestPowers[position])
        }
        holder.reqPowerDetails.setOnClickListener {
            val context = holder.reqPowerDetails.context
            val intent = Intent(context, UpdateBalanceOfRequestPower::class.java)
            context.startActivity(intent)
        }

        holder.nameTextView.text = listOfRequestPowers[position].name
        holder.reqPower.text = listOfRequestPowers[position].power.toString()

    }

    override fun getItemCount(): Int {
        return listOfRequestPowers.size
    }

    fun getRequestPower(position: Int): RequestPower {
        return listOfRequestPowers.get(position)
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    // View.OnClickListener
    {
        val nameTextView: TextView = view.findViewById(R.id.txt_object_name)
        val reqPower: TextView = view.findViewById(R.id.txt_request_power)
        val reqPowerDetails: TextView = view.findViewById(R.id.txt_request_power_details)
        val btnDelete: ImageView = view.findViewById(R.id.deleteRequestPower)

//        init {
//            itemView.setOnClickListener(this)
//        }

//        override fun onClick(v: View?) {
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                listener.onItemClick(position)
//            }
//        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}