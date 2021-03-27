package com.project.electrical_calculator.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.electrical_calculator.entities.VoltageDrop

@Dao
interface VoltageDropDao {

    @Insert
    fun insertVoltageDrop(voltageDrop: VoltageDrop)

    @Update
    fun updateVoltageDrop(voltageDrop: VoltageDrop)

    @Delete
    fun deleteVoltageDrop(voltageDrop: VoltageDrop)

    @Query("SELECT * FROM voltage_drop")
    fun getAllVoltageDrops(): LiveData<List<VoltageDrop>>

    @Query("DELETE FROM voltage_drop")
    fun deleteAllVoltageDrops()

    @Query("SELECT * FROM voltage_drop WHERE voltage_drop.id == :id")
    fun get(id: Long): LiveData<VoltageDrop>

}