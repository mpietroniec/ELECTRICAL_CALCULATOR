package com.project.electrical_calculator.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.electrical_calculator.entities.RequestPower

@Dao
interface RequestPowerDao {

    @Insert
    fun insert(requestPower: RequestPower)

    @Query("UPDATE balance_of_request_power SET name = :sName, surface = :sAmount, coefficientPower = :sCoefficientPower, power = :sPower WHERE id = :sId")
    fun update(sId: Long, sName: String, sAmount: Float, sCoefficientPower: Float, sPower: Float)

    @Delete
    fun delete(requestPower: RequestPower)

    @Query("SELECT * FROM balance_of_request_power")
    fun getAllPowers(): LiveData<List<RequestPower>>

    @Query("DELETE FROM balance_of_request_power")
    fun deleteAllRow()

    @Query("SELECT * FROM balance_of_request_power WHERE balance_of_request_power.id == :id")
    fun get(id: Long): LiveData<RequestPower>

//    @Query("Select *, SUM(power) FROM balance_of_request_power")
//    fun sumAllRequestPowers(): LiveData<List<RequestPower>>
}