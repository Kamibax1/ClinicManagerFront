package com.example.clinicmanagerfront.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.clinicmanagerfront.data.room.model.entity.MyAppointmentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyAppointmentDao {

    @Query("SELECT * FROM my_appointment")
    fun getAllMyAppointments() : Flow<List<MyAppointmentEntity>>

    @Query("SELECT * FROM my_appointment WHERE id_my_appointment = :id")
    suspend fun getMyAppointmentById(id: Int): MyAppointmentEntity?

    @Insert
    suspend fun insertAllMyAppointments(myAppointments: List<MyAppointmentEntity>)

    @Insert
    suspend fun insertMyAppointment(myAppointment: MyAppointmentEntity)

    @Query("DELETE FROM my_appointment")
    suspend fun deleteAllMyAppointments()
}