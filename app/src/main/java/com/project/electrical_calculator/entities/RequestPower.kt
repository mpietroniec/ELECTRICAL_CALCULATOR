package com.project.electrical_calculator.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance_of_request_power")
data class RequestPower(
    var name: String,
    var surface: Float,
    var coefficientPower: Float,
    var requestPower: Float
) {
    @PrimaryKey(autoGenerate = true)
    var objectNameID: Int = 0
}