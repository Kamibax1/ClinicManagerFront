package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.AppointmentFullModel
import com.example.clinicmanagerfront.data.model.AppointmentModel
import com.example.clinicmanagerfront.data.model.DoctorModel
import com.example.clinicmanagerfront.data.model.PatientModel
import com.example.clinicmanagerfront.data.model.StatusModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("patients")
    suspend fun getPatients() : List<PatientModel>

    @GET("appointments/find/doctor/1")
    suspend fun getAppointmentsByDoctorId() : List<AppointmentModel>

    @GET("appointments/find/status/COMPLETED")
    suspend fun getAppointmentsByStatus() : List<AppointmentModel>

    @GET("doctors")
    suspend fun getDoctors() : List<DoctorModel>

    @GET("patients/search/{partFullName}")
    suspend fun getPatientsByPartFullName(@Path("partFullName") partFullName: String) : List<PatientModel>

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id: Long) : PatientModel

    @GET("doctors/{id}")
    suspend fun getDoctorById(@Path("id") id: Long) : DoctorModel

    @GET("status/{id}")
    suspend fun getStatusById(@Path("id") id: Long) : StatusModel

    @GET("appointments")
    suspend fun getAppointments() : List<AppointmentModel>

    @GET("appointments/full")
    suspend fun getAppointmentsFull() : List<AppointmentFullModel>
}
