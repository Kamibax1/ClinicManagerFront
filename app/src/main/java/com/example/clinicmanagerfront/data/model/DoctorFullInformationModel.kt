package com.example.clinicmanagerfront.data.model

import com.google.gson.annotations.SerializedName

data class DoctorFullInformationModel(
    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("middle_name")
    val middleName: String,

    @SerializedName("experience_years")
    val experienceYears: Int,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("specializations")
    val specializations: Set<SpecializationModel>
)

//@Getter @Setter
//private String firstName;
//
//@Getter @Setter
//private String lastName;
//
//@Getter @Setter
//private String middleName;
//
//@Getter @Setter
//private int experienceYears;
//
//@Getter @Setter
//private String phoneNumber;
//
//@Getter @Setter
//private Set<SpecializationDTO> specializations;
