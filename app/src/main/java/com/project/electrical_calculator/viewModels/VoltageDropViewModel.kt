package com.project.electrical_calculator.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.electrical_calculator.entities.VoltageDrop
import com.project.electrical_calculator.repositories.VoltageDropRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class VoltageDropViewModel(application: Application) :
    AndroidViewModel(application) {
    private var voltageDropRepository: VoltageDropRepository =
        VoltageDropRepository(application)

    private var allVoltageDrops: Deferred<LiveData<List<VoltageDrop>>> =
        voltageDropRepository.getAllVoltageDropsAsync()

    fun insertVoltageDrop(voltageDrop: VoltageDrop) {
        voltageDropRepository.insertVoltageDrop(voltageDrop)
    }

    fun updateVoltageDrop(voltageDrop: VoltageDrop) {
        voltageDropRepository.updateVoltageDrop(voltageDrop)
    }

    fun get(id: Long) = voltageDropRepository.get(id)

    fun deleteVoltageDrop(voltageDrop: VoltageDrop) {
        voltageDropRepository.deleteVoltageDrop(voltageDrop)
    }

    fun getAllVoltageDrops(): LiveData<List<VoltageDrop>> = runBlocking {
        allVoltageDrops.await()
    }

    fun deleteAllDrops() {
        voltageDropRepository.deleteAllVoltageDrops()
    }

}