package com.project.electrical_calculator.electricBalance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.adapters.RequestPowerAdapter
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.viewModels.RequestPowerViewModel

class BalanceOfRequestPower : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: RequestPowerViewModel
    private lateinit var requestPowerAdapter: RequestPowerAdapter
    private lateinit var listOfRequestPower: LiveData<List<RequestPower>>


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


        val addButton = findViewById<Button>(R.id.btn_add_to_balance_of_request_power)
        addButton.setOnClickListener {
            val name = objectName.text.toString()
            val surface = objectSurface.text.toString().toFloat()
            val coefficientPower = objectPower.text.toString().toFloat()
            val objectRequestPower = surface * coefficientPower

            val requestPower = RequestPower(name, surface, coefficientPower, objectRequestPower)
            viewModel.insertRequestPower(requestPower)
        }

//        val deleteButton = findViewById<ImageView>(R.id.deleteRequestPower)
//        deleteButton.setOnClickListener {
//            viewModel.deleteRequestPower(requestPowerAdapter.getRequestPower(position = 1))
//        }


        recyclerView = findViewById(R.id.rv_balance_of_request_power)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listOfRequestPower = viewModel.getAllRequests()
        listOfRequestPower.observe(this, Observer {
            if (it.isNotEmpty()) {
                requestPowerAdapter = RequestPowerAdapter(it)
                recyclerView.adapter = requestPowerAdapter
            }
        })
    }
}

