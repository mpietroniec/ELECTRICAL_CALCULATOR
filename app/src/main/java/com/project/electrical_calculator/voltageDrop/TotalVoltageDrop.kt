package com.project.electrical_calculator.voltageDrop

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.electrical_calculator.R
import com.project.electrical_calculator.adapters.VoltageDropAdapter
import com.project.electrical_calculator.entities.VoltageDrop
import com.project.electrical_calculator.viewModels.VoltageDropViewModel
import java.text.DecimalFormat

class TotalVoltageDrop : AppCompatActivity(), VoltageDropAdapter.VoltageDropRowItemClickListener,
    AdapterView.OnItemSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var voltageDropViewModel: VoltageDropViewModel
    private lateinit var voltageDropAdapter: VoltageDropAdapter
    private var maxVoltageDrop: Float = 0F

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

        val voltageDropPower = findViewById<EditText>(R.id.etxt_voltage_drop_power)
        val voltageDropCableLength = findViewById<EditText>(R.id.etxt_cable_length)
        val voltageDropCableCross = findViewById<EditText>(R.id.etxt_cable_cross_section)

        fun voltageDropCalculation(): Float {
            val decimalFormat = DecimalFormat("##.##")
            if (spinnerPhase.selectedItem.toString() == "1-phase") {
                maxVoltageDrop = if (spinnerMaterial.selectedItem.toString() == "Cu") {
                    (200 *
                            voltageDropPower.text.toString()
                                .toFloat() * 1000 * voltageDropCableLength.text.toString().toFloat()
                            ) / (56 * voltageDropCableCross.text.toString().toFloat() * 230 * 230)
                } else {
                    (200 *
                            voltageDropPower.text.toString()
                                .toFloat() * 1000 * voltageDropCableLength.text.toString().toFloat()
                            ) / (33 * voltageDropCableCross.text.toString().toFloat() * 230 * 230)
                }
            } else {
                maxVoltageDrop = if (spinnerMaterial.selectedItem.toString() == "Cu") {
                    (100 *
                            voltageDropPower.text.toString()
                                .toFloat() * 1000 * voltageDropCableLength.text.toString().toFloat()
                            ) / (56 * voltageDropCableCross.text.toString().toFloat() * 400 * 400)
                } else {
                    (100 *
                            voltageDropPower.text.toString()
                                .toFloat() * 1000 * voltageDropCableLength.text.toString().toFloat()
                            ) / (33 * voltageDropCableCross.text.toString().toFloat() * 400 * 400)
                }
            }
            val decValue = decimalFormat.format(maxVoltageDrop)
            return decValue.toFloat()
        }

        val addVoltageDropButton = findViewById<Button>(R.id.btn_add_to_voltage_drop)
        addVoltageDropButton.setOnClickListener {
            val id = 0L
            val phase = spinnerPhase.selectedItem.toString()
            val material = spinnerMaterial.selectedItem.toString()
            val power = voltageDropPower.text.toString().toFloat()
            val length = voltageDropCableLength.text.toString().toFloat()
            val cableCrossSection = voltageDropCableCross.text.toString().toFloat()
            val voltageDropValue = voltageDropCalculation()
            val voltageDrop =
                VoltageDrop(id, phase, material, power, length, cableCrossSection, voltageDropValue)
            voltageDropViewModel.insertVoltageDrop(voltageDrop)
        }

        val deleteAllVoltageDropsButton = findViewById<TextView>(R.id.txt_delete_all_voltage_drops)
        deleteAllVoltageDropsButton.setOnClickListener {
            voltageDropViewModel.deleteAllDrops()
        }

        recyclerView = findViewById(R.id.rv_voltage_drop)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        voltageDropAdapter = VoltageDropAdapter(this)
        recyclerView.adapter = voltageDropAdapter

        voltageDropViewModel = ViewModelProviders.of(this).get(VoltageDropViewModel::class.java)
        voltageDropViewModel.getAllVoltageDrops().observe(this, Observer {
            voltageDropAdapter.setListData(ArrayList(it))
            voltageDropAdapter.notifyDataSetChanged()
        })
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

    override fun onDeleteVoltageDropClickListener(voltageDrop: VoltageDrop) {
        voltageDropViewModel.deleteVoltageDrop(voltageDrop)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}

