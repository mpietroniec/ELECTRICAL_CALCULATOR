package com.project.electrical_calculator.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.electrical_calculator.entities.RequestPower

@Dao
interface RequestPowerDao {

    @Insert
    fun insert(requestPower: RequestPower)

    @Update
    fun update(requestPower: RequestPower)

    @Delete
    fun delete(requestPower: RequestPower)

    @Query("SELECT * FROM balance_of_request_power")
    fun getAllPowers(): LiveData<List<RequestPower>>

    @Query("DELETE FROM balance_of_request_power")
    fun deleteAllRow()
}