package com.example.clinicmanagerfront.data.room.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_appointment")
data class MyAppointmentEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_my_appointment")
    val id: Long,

    val date: String,

    val time: String,

    val symptoms: String,

    val status: String,

    @ColumnInfo(name = "patient_id")
    val patientId: Long,

    @ColumnInfo(name = "doctor_id")
    val doctorId: Long
)