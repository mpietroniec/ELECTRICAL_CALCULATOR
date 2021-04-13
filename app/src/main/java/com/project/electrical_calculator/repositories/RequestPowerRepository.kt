package com.project.electrical_calculator.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.project.electrical_calculator.dao.RequestPowerDao
import com.project.electrical_calculator.entities.RequestPower
import com.project.electrical_calculator.helpers.ElectricalCalculatorDatabase
import kotlinx.coroutines.*

class RequestPowerRepository(application: Application) {
    private var requestPowerDao: RequestPowerDao

    init {
        val database = ElectricalCalculatorDatabase
            .getInstance(application.applicationContext)
        requestPowerDao = database!!.requestPowerDao()
    }

    fun insertRequestPower(requestPower: RequestPower) =
        CoroutineScope(Dispatchers.IO).launch {
            requestPowerDao.insert(requestPower)
        }

    fun updateRequestPower(requestPower: RequestPower) =
        CoroutineScope(Dispatchers.IO).launch {
            requestPowerDao.update(
                requestPower.id,
                requestPower.name,
                requestPower.surface,
                requestPower.coefficientPower,
                requestPower.power
            )
        }

//    fun sumRequestPowers() =
//        CoroutineScope(Dispatchers.IO).launch {
//            requestPowerDao.sumAllRequestPowers()
//        }

//    fun sumRequestPowersAsync(): Deferred<LiveData<List<RequestPower>>> =
//        CoroutineScope(Dispatchers.IO).async {
//            requestPowerDao.sumAllRequestPowers()
//        }

    fun get(id: Long): LiveData<RequestPower> {
        return requestPowerDao.get(id)
    }

    fun deleteRequestPower(requestPower: RequestPower) =
        CoroutineScope(Dispatchers.IO).launch {
            requestPowerDao.delete(requestPower)
        }

    fun getAllRequestPowerAsync(): Deferred<LiveData<List<RequestPower>>> =
        CoroutineScope(Dispatchers.IO).async {
            requestPowerDao.getAllPowers()
        }

    fun deleteAllRowsRequestPowers() =
        CoroutineScope(Dispatchers.IO).launch {
            requestPowerDao.deleteAllRow()
        }
}