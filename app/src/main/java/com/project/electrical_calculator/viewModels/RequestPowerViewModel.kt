package com.project.electrical_calculator.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.repositories.RequestPowerRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class RequestPowerViewModel(application: Application) :
    AndroidViewModel(application) {
    private var requestPowerRepository: RequestPowerRepository =
        RequestPowerRepository(application)

    private var allRequestPower: Deferred<LiveData<List<RequestPower>>> =
        requestPowerRepository.getAllRequestPowerAsync()

    fun insertRequestPower(requestPower: RequestPower) {
        requestPowerRepository.insertRequestPower(requestPower)
    }

    fun updateRequestPower(requestPower: RequestPower){
        requestPowerRepository.updateRequestPower(requestPower)
    }

    fun deleteRequestPower(requestPower: RequestPower){
        requestPowerRepository.deleteRequestPower(requestPower)
    }

    fun getAllRequests(): LiveData<List<RequestPower>> = runBlocking {
        allRequestPower.await()
    }

    fun deleteAllRequests(){
        requestPowerRepository.deleteAllRowsRequestPowers()
    }
}