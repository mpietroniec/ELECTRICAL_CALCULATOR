package com.project.electrical_calculator.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.electrical_calculator.dao.RequestPowerDao
import com.project.electrical_calculator.entities.RequestPower

@Database(entities = [RequestPower::class], version = 4, exportSchema = false)
abstract class RequestPowerDatabase : RoomDatabase() {
    abstract fun requestPowerDao(): RequestPowerDao

    companion object {
        private var instance: RequestPowerDatabase? = null
        fun getInstance(context: Context): RequestPowerDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    RequestPowerDatabase::class.java,
                    "balance_of_request_power"
                )
                    .fallbackToDestructiveMigration()

                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}