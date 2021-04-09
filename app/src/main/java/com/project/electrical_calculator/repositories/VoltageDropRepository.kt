package com.project.electrical_calculator.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.project.electrical_calculator.dao.VoltageDropDao
import com.project.electrical_calculator.entities.VoltageDrop
import com.project.electrical_calculator.helpers.RequestPowerDatabase
import kotlinx.coroutines.*

class VoltageDropRepository(application: Application) {
    private var voltageDropDao: VoltageDropDao

    init {
        val database = RequestPowerDatabase
            .getInstance(application.applicationContext)
        voltageDropDao = database!!.voltageDropDao()
    }

    fun insertVoltageDrop(voltageDrop: VoltageDrop) =
        CoroutineScope(Dispatchers.IO).launch {
            voltageDropDao.insertVoltageDrop(voltageDrop)
        }

    fun updateVoltageDrop(voltageDrop: VoltageDrop) =
        CoroutineScope(Dispatchers.IO).launch {
            voltageDropDao.updateVoltageDrop(
                voltageDrop.id,
                voltageDrop.phase,
                voltageDrop.material,
                voltageDrop.length,
                voltageDrop.power,
                voltageDrop.cableCrossSection,
                voltageDrop.voltageDrop
            )
        }

    fun get(id: Long): LiveData<VoltageDrop> {
        return voltageDropDao.get(id)
    }

    fun deleteVoltageDrop(voltageDrop: VoltageDrop) =
        CoroutineScope(Dispatchers.IO).launch {
            voltageDropDao.deleteVoltageDrop(voltageDrop)
        }

    fun getAllVoltageDropsAsync(): Deferred<LiveData<List<VoltageDrop>>> =
        CoroutineScope(Dispatchers.IO).async {
            voltageDropDao.getAllVoltageDrops()
        }

    fun deleteAllVoltageDrops() =
        CoroutineScope(Dispatchers.IO).launch {
            voltageDropDao.deleteAllVoltageDrops()
        }

}