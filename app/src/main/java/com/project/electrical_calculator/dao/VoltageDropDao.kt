package com.project.electrical_calculator.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.project.electrical_calculator.entities.VoltageDrop

@Dao
interface VoltageDropDao {

    @Insert
    fun insertVoltageDrop(voltageDrop: VoltageDrop)

    @Query("UPDATE voltage_drop SET phase = :sPhase, material = :sMaterial, power = :sPower, length = :sLength, cableCrossSection = :sCableCrossSection, voltageDrop = :sVoltageDrop WHERE id = :sID")
    fun updateVoltageDrop(
        sId: Long,
        sPhase: String,
        sMaterial: String,
        sPower: Float,
        sLength: Float,
        sCableCrossSection: Float,
        sVoltageDrop: Float
    )

    @Delete
    fun deleteVoltageDrop(voltageDrop: VoltageDrop)

    @Query("SELECT * FROM voltage_drop")
    fun getAllVoltageDrops(): LiveData<List<VoltageDrop>>

    @Query("DELETE FROM voltage_drop")
    fun deleteAllVoltageDrops()

    @Query("SELECT * FROM voltage_drop WHERE voltage_drop.id == :id")
    fun get(id: Long): LiveData<VoltageDrop>

}