package com.project.electrical_calculator.voltageDrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import com.project.electrical_calculator.R

class VoltageDrop : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voltage_drop)

        val spinnerPhase: Spinner = findViewById(R.id.spin_phases_voltage_drop)
        ArrayAdapter.createFromResource(
            this,
            R.array.phases,
            R.layout.activity_spinner_phases
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPhase.adapter = arrayAdapter
        }

        val spinnerMaterial: Spinner = findViewById(R.id.spin_material_voltage_drop)
        ArrayAdapter.createFromResource(
            this,
            R.array.material,
            R.layout.activity_spinner_material
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMaterial.adapter = arrayAdapter
        }
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_info_request_power, menu)
        return true
    }

    fun startRequestPowerInfo(item: MenuItem) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Calculation method")
        builder.setMessage("...")
        builder.setNeutralButton("OK") { _, _ ->

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}