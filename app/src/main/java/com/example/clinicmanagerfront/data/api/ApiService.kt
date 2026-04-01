package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.AppointmentModel
import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel
import retrofit2.http.GET

interface ApiService {
    @GET("main/doctor/patients")
    suspend fun getPatients() : List<PatientModel>

    @GET("main/doctor/doctor/appointments/1")
    suspend fun getAppointmentsByDoctorId() : List<AppointmentModel>

    @GET("main/doctor/status/appointments/COMPLETED")
    suspend fun getAppointmentsByStatus() : List<AppointmentModel>

    @GET("main/doctor/doctors")
    suspend fun getDoctors() : List<DoctorModel>
}