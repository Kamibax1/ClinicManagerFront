package com.example.clinicmanagerfront.data.api

import com.example.clinicmanagerfront.data.model.*
import com.example.clinicmanagerfront.data.model.HomeStatsModel
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.model.enums.StatusEnum
import retrofit2.http.*

interface ApiService {
    //POST(ADMIN)
    @POST("admin/users")
    suspend fun saveUser(@Body userRequest: CreateUserModel) : UserResponse

    //POST(PATIENT)
    @POST("patient/appointments")
    suspend fun saveAppointment(@Body appointment: CreateAppointmentModel) : CreateAppointmentModel

    //GET(ADMIN)
    @GET("admin/users")
    suspend fun getAllUsers() : List<UserResponse>

    @GET("admin/users/containing/username/{username}")
    suspend fun getAllUsersByPartUsername(@Path("username") username: String) : List<UserResponse>

    @GET("admin/users/order/username")
    suspend fun getAllUsersByOrderUsername() : List<UserResponse>

    @GET("admin/users/order/email")
    suspend fun getAllUsersByOrderEmail() : List<UserResponse>

    @GET("admin/users/role/{roleName}")
    suspend fun getAllUsersByRoleName(@Path("roleName") roleName: RoleEnum) : List<UserResponse>

    @GET("admin/users/enabled/{enabled}")
    suspend fun getAllUsersByEnabled(@Path("enabled") enabled: Boolean) : List<UserResponse>

    //GET(DOCTOR)
    @GET("doctor/appointments/information/short")
    suspend fun getAllAppointmentsShortInfo(): List<AppointmentShortInformationModel>

    @GET("doctor/home/stats")
    suspend fun getHomeStats() : HomeStatsModel

    @GET("doctor/patients/information/short")
    suspend fun getAllPatientsShortInfo() : List<PatientShortInformationModel>

    @GET("doctor/appointments/information/short/doctor/{partDoctorName}")
    suspend fun getAllAppointmentsShortInfoByDoctorName(@Path("partDoctorName") partDoctorName: String) : List<AppointmentShortInformationModel>

    @GET("doctor/patients/information/short/{name}")
    suspend fun getAllPatientsShortInfoByName(@Path("name") name: String) : List<PatientShortInformationModel>

    @GET("doctor/users/me/information/short/{username}")
    suspend fun getShortInformationDoctorByUsername(@Path("username") username: String) : DoctorShortInformationModel

    @GET("doctor/users/me/information/full/{username}")
    suspend fun getFullInformationDoctorByUsername(@Path("username") username: String) : DoctorFullInformationModel

    @GET("doctor/appointments/information/short/status/{status}")
    suspend fun getAllAppointmentsShortInfoByStatus(@Path("status") status: StatusEnum) : List<AppointmentShortInformationModel>

    @GET("doctor/profile/stats/{id}")
    suspend fun getProfileStatsDoctor(@Path("id") id: Long) : ProfileStatsModel

    //GET(PATIENT)
    @GET("patient/appointments/information/full/{id}")
    suspend fun getAppointmentFullInfoById(@Path("id") id: Long) : AppointmentFullInformationModel

    @GET("patient/doctors/information/short")
    suspend fun getAllDoctorsShortInfo() : List<DoctorShortInformationModel>

    @GET("patient/specializations")
    suspend fun getAllDoctorsSpecializations() : List<SpecializationModel>

    @GET("patient/status")
    suspend fun getAllStatus() : List<StatusModel>

    @GET("patient/appointments/information/short/patient/{patientId}/doctor/{partDoctorName}")
    suspend fun getAllAppointmentsShortInfoByPatientIdAndPartDoctorName(
        @Path("patientId") patientId: Long,
        @Path("partDoctorName") partDoctorName: String
    ) : List<AppointmentShortInformationModel>

    @GET("patient/doctors/information/short/name/{name}")
    suspend fun getAllDoctorsShortInfoByName(@Path("name") name: String) : List<DoctorShortInformationModel>

    @GET("patient/users/me/information/short/{username}")
    suspend fun getShortInformationPatientByUsername(@Path("username") username: String) : PatientShortInformationModel

    @GET("patient/users/me/information/full/{username}")
    suspend fun getFullInformationPatientByUsername(@Path("username") username: String) : PatientFullInformationForUpdatePatientModel

    @GET("patient/doctors/information/short/specialization/{specialization}")
    suspend fun getAllDoctorsShortInfoBySpecialization(@Path("specialization") specialization: String) : List<DoctorShortInformationModel>

    @GET("patient/appointments/information/short/{patientId}")
    suspend fun getAllAppointmentsShortInfoByPatientId(@Path("patientId") patientId: Long) : List<AppointmentShortInformationModel>

    @GET("patient/appointments/information/short/patient/{patientId}/status/{status}")
    suspend fun getAllAppointmentsShortInfoByPatientIdAndStatus(
        @Path("patientId") patientId: Long,
        @Path("status") status: StatusEnum
    ) : List<AppointmentShortInformationModel>

    @GET("patient/profile/stats/{id}")
    suspend fun getProfileStatsPatient(@Path("id") id: Long) : ProfileStatsModel

    //PUT(ADMIN)
    @PUT("admin/appointments/information/full/status/{id}")
    suspend fun updateAppointmentStatus(
        @Path("id") id: Long,
        @Body status: StatusEnum
    ) : AppointmentFullInformationModel

    @PUT("admin/appointments/information/full/symptoms/{id}")
    suspend fun updateAppointmentSymptoms(
        @Path("id") id: Long,
        @Body symptoms: String
    ) : AppointmentFullInformationModel

    @PUT("admin/users/enabled/{id}")
    suspend fun updateUserEnabled(@Path("id") id: Long) : UserResponse

    @PUT("admin/users/role/{id}")
    suspend fun updateUserRole(
        @Path("id") id: Long,
        @Body role: RoleEnum
    ) : UserResponse

    //PUT(DOCTOR)
    @PUT("doctor/appointments/information/full/status/{id}")
    suspend fun updateDoctorAppointmentStatus(
        @Path("id") id: Long,
        @Body model: UpdateAppointmentStatusModel
    ) : AppointmentFullInformationModel

    @PUT("doctor/appointments/information/full/symptoms/{id}")
    suspend fun updateDoctorAppointmentSymptoms(
        @Path("id") id: Long,
        @Body model: UpdateAppointmentSymptomsModel
    ) : AppointmentFullInformationModel

    @PUT("doctor/doctors/information/full/{id}")
    suspend fun updateDoctorFullInfo(
        @Path("id") id: Long,
        @Body doctor: UpdateDoctorFullInformationRequest
    ) : DoctorFullInformationModel

    //PUT(PATIENT)
    @PUT("patient/patients/information/full/{id}")
    suspend fun updatePatientFullInfo(
        @Path("id") id: Long,
        @Body patient: UpdatePatientFullInformationRequest
    ) : PatientFullInformationForUpdatePatientModel

    //DELETE(ADMIN)
    @DELETE("admin/appointments/{id}")
    suspend fun deleteAppointmentById(@Path("id") id: Long)
}
