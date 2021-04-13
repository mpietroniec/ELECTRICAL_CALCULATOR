package com.project.electrical_calculator.electricBalance

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

        val addButton = findViewById<Button>(R.id.btn_add_to_balance_of_request_power)
        addButton.setOnClickListener {
            val id = 0L
            val name = objectName.text.toString()
            val surface = objectSurface.text.toString().toFloat()
            val coefficientPower = objectPower.text.toString().toFloat()
            val power = surface * coefficientPower

            val requestPower = RequestPower(id, name, surface, coefficientPower, power)
            viewModel.insertRequestPower(requestPower)

            objectName.setText("")
            objectSurface.setText("")
            objectPower.setText("")

        }

        val deleteAllButton = findViewById<TextView>(R.id.txt_delete_all)
        deleteAllButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete all?")
            builder.setMessage("Are you sure you want to delete everything?")
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.deleteAllRequests()
            }
            builder.setNegativeButton("No") { _, _ ->

            }
            builder.show()
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
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

    override fun onDeleteRequestClickListener(requestPower: RequestPower) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete this?")
        builder.setMessage("Are you sure you want to delete this?")
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteRequestPower(requestPower)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.show()

    }
}

