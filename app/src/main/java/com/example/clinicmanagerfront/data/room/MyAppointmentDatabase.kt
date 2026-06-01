package com.example.clinicmanagerfront.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clinicmanagerfront.data.room.dao.MyAppointmentDao
import com.example.clinicmanagerfront.data.room.model.entity.MyAppointmentEntity

@Database(
    entities = [MyAppointmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyAppointmentDatabase : RoomDatabase() {
    abstract fun myAppointmentDao(): MyAppointmentDao
}