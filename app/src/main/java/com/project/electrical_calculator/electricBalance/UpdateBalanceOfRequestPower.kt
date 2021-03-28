package com.project.electrical_calculator.electricBalance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.project.electrical_calculator.R
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.viewModels.RequestPowerViewModel

class UpdateBalanceOfRequestPower : AppCompatActivity() {

    private lateinit var viewModel: RequestPowerViewModel

    companion object {
        const val REQUEST_POWER_NAME = "request_power_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_balance_of_request_power_update)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(RequestPowerViewModel::class.java)

        val requestPower: RequestPower = intent.getParcelableExtra(REQUEST_POWER_NAME)!!

        val updateName = findViewById<EditText>(R.id.etxt_update_electric_balance_name)
        val updateSurface = findViewById<EditText>(R.id.etxt_update_electric_balance_surface)
        val updateCoefficientPower = findViewById<EditText>(R.id.etxt_update_electric_balance_power)
        val updatePower = findViewById<TextView>(R.id.txt_update_power)

        updateName.setText(requestPower.name)
        updateSurface.setText(requestPower.surface.toString())
        updateCoefficientPower.setText(requestPower.coefficientPower.toString())
        updatePower.text = requestPower.power.toString()

//        val btnUpdate = findViewById<Button>(R.id.btn_update)
//        btnUpdate.setOnClickListener {
//val id = requestPower?.id?.toLong()
//            val name = updateName.text.toString()
//            val surface = updateSurface.text.toString().toFloat()
//            val coefficientPower = updateCoefficientPower.text.toString().toFloat()
//            val power = surface * coefficientPower
//
//            val reqPower = id?.let { it1 -> RequestPower(id = it1, name = name, surface = surface, coefficientPower = coefficientPower, power = power) }
//            viewModel.updateRequestPower(reqPower)
//            finish()
//        }
    }
}