package com.project.electrical_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.project.electrical_calculator.cableSelection.CableSelection
import com.project.electrical_calculator.electricBalance.BalanceOfRequestPower
import com.project.electrical_calculator.voltageDrop.TotalVoltageDrop

class MainActivity : AppCompatActivity() {

    private lateinit var btnElectricBalance: Button
    private lateinit var btnVoltageDrop:Button
    private lateinit var btnCableSelection: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnElectricBalance = findViewById(R.id.btn_electric_balance)
        btnElectricBalance.setOnClickListener {
            val intent = Intent(this, BalanceOfRequestPower::class.java)
            startActivity(intent)
        }

        btnVoltageDrop = findViewById(R.id.btn_voltage_drop)
        btnVoltageDrop.setOnClickListener {
            val intent = Intent(this,TotalVoltageDrop::class.java)
            startActivity(intent)
        }

        btnCableSelection = findViewById(R.id.btn_cable_selection)
        btnCableSelection.setOnClickListener {
            val intent = Intent(this, CableSelection::class.java)
            startActivity(intent)
        }
    }
}