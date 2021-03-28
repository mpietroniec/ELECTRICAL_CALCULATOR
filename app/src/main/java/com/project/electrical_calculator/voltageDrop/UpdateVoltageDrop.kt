package com.project.electrical_calculator.voltageDrop

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.project.electrical_calculator.R
import com.project.electrical_calculator.entities.VoltageDrop
import com.project.electrical_calculator.viewModels.VoltageDropViewModel

class UpdateVoltageDrop : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var updateVoltageDropViewModel: VoltageDropViewModel

    companion object {
        const val VOLTAGE_DROP_NAME = "voltage_drop_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voltage_drop_update)

        updateVoltageDropViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(VoltageDropViewModel::class.java)

        val voltageDrop: VoltageDrop = intent.getParcelableExtra(VOLTAGE_DROP_NAME)!!

        val updateSpinnerPhase: Spinner = findViewById(R.id.spin_update_phases_voltage_drop)
        ArrayAdapter.createFromResource(
            this,
            R.array.phases,
            R.layout.activity_spinner_phases
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            updateSpinnerPhase.adapter = arrayAdapter
            updateSpinnerPhase.setSelection(arrayAdapter.getPosition(voltageDrop.phase))
        }

        val updateSpinnerMaterial: Spinner = findViewById(R.id.spin_update_material_voltage_drop)
        ArrayAdapter.createFromResource(
            this,
            R.array.material,
            R.layout.activity_spinner_material
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            updateSpinnerMaterial.adapter = arrayAdapter
            updateSpinnerMaterial.setSelection(arrayAdapter.getPosition(voltageDrop.material))
        }

        val updateVoltageDropValue = findViewById<TextView>(R.id.txt_update_voltage_drop_value)
        val updateVoltageDropPower = findViewById<EditText>(R.id.etxt_update_voltage_drop_power)
        val updateVoltagePowerLength = findViewById<EditText>(R.id.etxt_update_cable_length)
        val updateCableCrossSection = findViewById<EditText>(R.id.etxt_update_cable_cross_section)

        updateVoltageDropValue.text = voltageDrop.voltageDrop.toString()
        updateVoltageDropPower.setText(voltageDrop.power.toString())
        updateVoltagePowerLength.setText(voltageDrop.length.toString())
        updateCableCrossSection.setText(voltageDrop.cableCrossSection.toString())

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}