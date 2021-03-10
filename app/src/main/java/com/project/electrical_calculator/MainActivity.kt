package com.project.electrical_calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.project.electrical_calculator.electricBalance.BalanceOfRequestPower

private lateinit var btnElectricBalance: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnElectricBalance = findViewById(R.id.btn_electric_balance)
        btnElectricBalance.setOnClickListener {
            val intent = Intent(this, BalanceOfRequestPower::class.java)
            startActivity(intent)
        }
    }
}