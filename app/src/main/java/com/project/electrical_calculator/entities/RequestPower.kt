package com.project.electrical_calculator.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance_of_request_power")
data class RequestPower(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var surface: Float,
    var coefficientPower: Float,
    var power: Float
) : Parcelable {
//    @PrimaryKey(autoGenerate = true)
//    var objectNameID: Int = 0
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeFloat(surface)
        parcel.writeFloat(coefficientPower)
        parcel.writeFloat(power)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestPower> {
        override fun createFromParcel(parcel: Parcel): RequestPower {
            return RequestPower(parcel)
        }

        override fun newArray(size: Int): Array<RequestPower?> {
            return arrayOfNulls(size)
        }
    }
}