package com.project.electrical_calculator.electricBalance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.adapters.RequestPowerAdapter
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.viewModels.RequestPowerViewModel

class BalanceOfRequestPower : AppCompatActivity(), RequestPowerAdapter.RowItemClickListener {

    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RequestPowerViewModel
    private lateinit var requestPowerAdapter: RequestPowerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance_of_request_power)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(RequestPowerViewModel::class.java)

        val objectName = findViewById<EditText>(R.id.etxt_electric_balance_name_item)
        val objectSurface = findViewById<EditText>(R.id.etxt_electric_balance_surface)
        val objectPower = findViewById<EditText>(R.id.etxt_electric_balance_power)
        var totalPower = findViewById<TextView>(R.id.txt_total_power)


        val addButton = findViewById<Button>(R.id.btn_add_to_balance_of_request_power)
        addButton.setOnClickListener {
            val id = 0L
            val name = objectName.text.toString()
            val surface = objectSurface.text.toString().toFloat()
            val coefficientPower = objectPower.text.toString().toFloat()
            val power = surface * coefficientPower

            val requestPower = RequestPower(id, name, surface, coefficientPower, power)
            viewModel.insertRequestPower(requestPower)
        }

        val deleteAllButton = findViewById<TextView>(R.id.txt_delete_all)
        deleteAllButton.setOnClickListener {
            viewModel.deleteAllRequests()
        }

        recyclerView = findViewById(R.id.rv_balance_of_request_power)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        requestPowerAdapter = RequestPowerAdapter(this)
        recyclerView.adapter = requestPowerAdapter

        viewModel = ViewModelProviders.of(this).get(RequestPowerViewModel::class.java)
        viewModel.getAllRequests().observe(this, Observer {
            requestPowerAdapter.setListData(ArrayList(it))
            requestPowerAdapter.notifyDataSetChanged()
        })
//        viewModel = ViewModelProviders.of(this).get(RequestPowerViewModel::class.java)
//        totalPower.text = viewModel.sumRequestPowers().toString()
    }

    override fun onDeleteRequestClickListener(requestPower: RequestPower) {
        viewModel.deleteRequestPower(requestPower)
    }
}

