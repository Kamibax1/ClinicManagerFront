package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.HomeStatsResponse
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import retrofit2.http.*

interface ApiService {

    @GET("appointments/information/short")
    suspend fun getAllAppointmentsShortInfo(): List<AppointmentShortInformationResponse>

    @GET("appointments/information/full/{id}")
    suspend fun getAppointmentFullInfoById(@Path("id") id: Long) : AppointmentFullInformationResponse

    @PUT("appointments/information/{id}")
    suspend fun updateStatus(
        @Path("id") id : Long,
        @Body status: StatusEnum
    ) : AppointmentFullInformationResponse

    @GET("doctors/information/short")
    suspend fun getAllDoctorsShortInfo() : List<DoctorShortInfoResponse>

    @GET("specializations")
    suspend fun getAllDoctorsSpecializations() : List<SpecializationModel>

    @GET("home/stats")
    suspend fun getHomeStats() : HomeStatsResponse

    @GET("patients/information/short")
    suspend fun getAllPatientsShortInfo() : List<PatientShortInfoResponse>

    @GET("status")
    suspend fun getAllStatus() : List<StatusModel>

    @GET("status/{id}")
    suspend fun getStatusById(@Path("id") id: Long) : StatusModel

    @GET("appointments/information/short/{doctorName}")
    suspend fun getAllAppointmentsShortInfoByDoctorName(@Path("doctorName") doctorName: String) : List<AppointmentShortInformationResponse>

    @GET("doctors/information/short/{name}")
    suspend fun getAllDoctorsShortInfoByName(@Path("name") name: String) : List<DoctorShortInfoResponse>

    @GET("patients/information/short/{name}")
    suspend fun getAllPatientsShortInfoByName(@Path("name") name: String) : List<PatientShortInfoResponse>

//    @PUT("appointments/information/{id}")
//    suspend fun updateAppointmentStatus(
//        @Path("id") id: Long,
//        @Body statusUpdate: StatusEnum
//    ) : AppointmentInformationModel

//    @POST("appointments")
//    suspend fun createAppointment(@Body appointmentRequest: CreateAppointmentRequest) : CreateAppointmentRequest
}
