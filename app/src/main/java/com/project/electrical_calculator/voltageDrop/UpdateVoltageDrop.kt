package com.project.electrical_calculator.voltageDrop

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.project.electrical_calculator.R
import com.project.electrical_calculator.entities.VoltageDrop
import com.project.electrical_calculator.viewModels.VoltageDropViewModel
import java.text.DecimalFormat

class UpdateVoltageDrop : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var updateVoltageDropViewModel: VoltageDropViewModel
    private var maxVoltageDrop: Float = 0F

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
        val updateVoltageDropLength = findViewById<EditText>(R.id.etxt_update_cable_length)
        val updateCableCrossSection = findViewById<EditText>(R.id.etxt_update_cable_cross_section)


        updateVoltageDropValue.setText(voltageDrop.voltageDrop.toString())
        updateVoltageDropPower.setText(voltageDrop.power.toString())
        updateVoltageDropLength.setText(voltageDrop.length.toString())
        updateCableCrossSection.setText(voltageDrop.cableCrossSection.toString())

        fun voltageDropCalculation(): Float {
            val decimalFormat = DecimalFormat("##.##")
            if (updateSpinnerPhase.selectedItem.toString() == "1-phase") {
                maxVoltageDrop = if (updateSpinnerMaterial.selectedItem.toString() == "Cu") {
                    (200 *
                            updateVoltageDropPower.text.toString()
                                .toFloat() * 1000 * updateVoltageDropLength.text.toString()
                        .toFloat()
                            ) / (56 * updateCableCrossSection.text.toString().toFloat() * 230 * 230)
                } else {
                    (200 *
                            updateVoltageDropPower.text.toString()
                                .toFloat() * 1000 * updateVoltageDropLength.text.toString()
                        .toFloat()
                            ) / (33 * updateCableCrossSection.text.toString().toFloat() * 230 * 230)
                }
            } else {
                maxVoltageDrop = if (updateSpinnerMaterial.selectedItem.toString() == "Cu") {
                    (100 *
                            updateVoltageDropPower.text.toString()
                                .toFloat() * 1000 * updateVoltageDropLength.text.toString()
                        .toFloat()
                            ) / (56 * updateCableCrossSection.text.toString().toFloat() * 400 * 400)
                } else {
                    (100 *
                            updateVoltageDropPower.text.toString()
                                .toFloat() * 1000 * updateVoltageDropLength.text.toString()
                        .toFloat()
                            ) / (33 * updateCableCrossSection.text.toString().toFloat() * 400 * 400)
                }
            }
            val decValue = decimalFormat.format(maxVoltageDrop)
            return decValue.toFloat()
        }

        val btnUpdate = findViewById<Button>(R.id.btn_update_add_to_voltage_drop)
        btnUpdate.setOnClickListener {
            val id = voltageDrop.id
            val phase = updateSpinnerPhase.selectedItem.toString()
            val material = updateSpinnerMaterial.selectedItem.toString()
            val power = updateVoltageDropPower.text.toString().toFloat()
            val length = updateVoltageDropLength.text.toString().toFloat()
            val cableSelection = updateCableCrossSection.text.toString().toFloat()
            val voltageDropValue = voltageDropCalculation()
            val voltageDrop = VoltageDrop(
                id = id,
                phase = phase,
                material = material,
                power = power,
                length = length,
                cableCrossSection = cableSelection,
                voltageDrop = voltageDropValue
            )
            updateVoltageDropViewModel.updateVoltageDrop(voltageDrop)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}