package com.project.electrical_calculator.electricBalance

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.electrical_calculator.R
import com.project.electrical_calculator.viewModels.RequestPowerViewModel

class UpdateBalanceOfRequestPower : AppCompatActivity() {

    private lateinit var requestPowerViewModel: RequestPowerViewModel
    private var recordID: Long = 0L
    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_balance_of_request_power)

        requestPowerViewModel = ViewModelProvider(this).get(RequestPowerViewModel::class.java)
        if (intent.hasExtra(Constants.DATA_RECORD_ID)) {
            recordID = intent.getLongExtra(Constants.DATA_RECORD_ID, 0L)
            requestPowerViewModel.get(recordID).observe(this, Observer {
                val updateName = findViewById<EditText>(R.id.etxt_update_electric_balance_name)
                val updateSurface =
                    findViewById<EditText>(R.id.etxt_update_electric_balance_surface)
                val updatePower = findViewById<EditText>(R.id.etxt_update_electric_balance_power)
                if (it != null) {
                    updateName.setText(it.name)

                }
            })
            isEdit = true
        }
    }
}