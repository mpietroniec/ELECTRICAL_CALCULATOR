package com.project.electrical_calculator.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voltage_drop")
data class VoltageDrop(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var phase: String,
    var material: String,
    var power: Float,
    var length: Float,
    var cableCrossSection: Float,
    var voltageDrop: Float
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(phase)
        parcel.writeString(material)
        parcel.writeFloat(power)
        parcel.writeFloat(length)
        parcel.writeFloat(cableCrossSection)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<VoltageDrop> {
        override fun createFromParcel(parcel: Parcel): VoltageDrop {
            return VoltageDrop(parcel)
        }

        override fun newArray(size: Int): Array<VoltageDrop?> {
            return arrayOfNulls(size)
        }
    }

}