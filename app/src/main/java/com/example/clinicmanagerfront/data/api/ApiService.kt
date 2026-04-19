package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.HomeStatsModel
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import retrofit2.http.*

interface ApiService {

    @GET("appointments/information/short")
    suspend fun getAllAppointmentsShortInfo(): List<AppointmentShortInformationModel>

    @GET("appointments/information/full/{id}")
    suspend fun getAppointmentFullInfoById(@Path("id") id: Long) : AppointmentFullInformationModel

    @PUT("appointments/information/{id}")
    suspend fun updateStatus(
        @Path("id") id : Long,
        @Body status: StatusEnum
    ) : AppointmentFullInformationModel

    @GET("doctors/information/short")
    suspend fun getAllDoctorsShortInfo() : List<DoctorShortInformationModel>

    @GET("specializations")
    suspend fun getAllDoctorsSpecializations() : List<SpecializationModel>

    @GET("home/stats")
    suspend fun getHomeStats() : HomeStatsModel

    @GET("patients/information/short")
    suspend fun getAllPatientsShortInfo() : List<PatientShortInformationModel>

    @GET("status")
    suspend fun getAllStatus() : List<StatusModel>

    @GET("status/{id}")
    suspend fun getStatusById(@Path("id") id: Long) : StatusModel

    @GET("appointments/information/short/{doctorName}")
    suspend fun getAllAppointmentsShortInfoByDoctorName(@Path("doctorName") doctorName: String) : List<AppointmentShortInformationModel>

    @GET("doctors/information/short/{name}")
    suspend fun getAllDoctorsShortInfoByName(@Path("name") name: String) : List<DoctorShortInformationModel>

    @GET("patients/information/short/{name}")
    suspend fun getAllPatientsShortInfoByName(@Path("name") name: String) : List<PatientShortInformationModel>

    @POST("appointments")
    suspend fun saveAppointment(@Body appointment: CreateAppointmentModel) : CreateAppointmentModel

    @GET("status/search/name/{name}")
    suspend fun getStatusByName(@Path("name") name: StatusEnum) : StatusModel

    @DELETE("appointments/{id}")
    suspend fun deleteAppointmentById(@Path("id") id: Long)

//    @POST("appointments")
//    suspend fun createAppointment(@Body appointmentRequest: CreateAppointmentRequest) : CreateAppointmentRequest
}
