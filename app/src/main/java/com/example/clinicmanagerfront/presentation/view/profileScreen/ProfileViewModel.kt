package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.repository.TokenManager
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.UpdateDoctorFullInformationRequest
import com.example.clinicmanagerfront.data.model.UpdatePatientFullInformationRequest
import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.data.repository.UserRepository
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent.ProfileFormUiEvent
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiEvent.ProfileUiEvent
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileFormUiState
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val tokenManager: TokenManager,
    userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _uiStateForm = MutableStateFlow(ProfileFormUiState())
    val uiStateForm: StateFlow<ProfileFormUiState> = _uiStateForm.asStateFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            val currentUser: StateFlow<UserResponse?> = userRepository.currentUser
            _uiState.update { it.copy(user = currentUser.value) }
            if (currentUser.value?.role == RoleEnum.PATIENT) {
                loadStatsPatient()
            } else if (currentUser.value?.role == RoleEnum.DOCTOR) {
                loadStatsDoctor()
            }
            loadInfo()
        }
    }

    fun postFormUiEvent(event: ProfileFormUiEvent) {
        when(event) {
            is ProfileFormUiEvent.OnChangeFirstName -> onChangeFirstName(event.newFirstName)
            is ProfileFormUiEvent.OnChangeLastName -> onChangeLastName(event.newLastName)
            is ProfileFormUiEvent.OnChangeMiddleName -> oChangeMiddleName(event.newMiddleName)
            is ProfileFormUiEvent.OnChangeDateOfBirth -> onChangeDateOfBirth(event.newDateOfBirth)
            is ProfileFormUiEvent.OnChangeGender -> onChangeGender(event.newGender)
            is ProfileFormUiEvent.OnChangePhoneNumber -> onChangePhoneNumber(event.newPhoneNumber)
            is ProfileFormUiEvent.LoadInfo -> loadInfo()
            is ProfileFormUiEvent.UpdateInfo -> updateInfo()
        }
    }

    fun postUiEvent(event: ProfileUiEvent) {
        when(event) {
            is ProfileUiEvent.OnUpdateStatusForm -> onUpdateStatusForm()
            is ProfileUiEvent.OnUpdateStatusLogoutDialog -> onUpdateStatusLogoutDialog()
        }
    }

    fun logout() {
        tokenManager.clearAll()
        viewModelScope.launch {
            _logoutEvent.emit(Unit)
        }
    }

    fun onChangeFirstName(newFirstName: String?) : String? {
        _uiStateForm.update { it.copy(firstName = newFirstName) }
        return _uiStateForm.value.firstName
    }

    fun onChangeLastName(newLastName: String?)  : String? {
        _uiStateForm.update { it.copy(lastName = newLastName) }
        return _uiStateForm.value.lastName
    }

    fun oChangeMiddleName(newMiddleName: String?) : String? {
        _uiStateForm.update { it.copy(middleName = newMiddleName) }
        return _uiStateForm.value.middleName
    }

    fun onChangeDateOfBirth(newDateOfBirth: String?) : String? {
        _uiStateForm.update { it.copy(dateOfBirth = newDateOfBirth) }
        return _uiStateForm.value.dateOfBirth
    }

    fun onChangeGender(newGender: String?) : String? {
        _uiStateForm.update { it.copy(gender = newGender) }
        return _uiStateForm.value.gender
    }

    fun onChangePhoneNumber(newPhoneNumber: String?) : String? {
        _uiStateForm.update { it.copy(phoneNumber = newPhoneNumber) }
        return _uiStateForm.value.phoneNumber
    }

    fun onUpdateStatusForm() {
        _uiState.update { it.copy(onOpenForm = !it.onOpenForm) }
    }

    fun onUpdateStatusLogoutDialog() {
        _uiState.update { it.copy(showLogoutDialog = !it.showLogoutDialog) }
    }

    fun loadInfo() {
        _uiState.update { it.copy(isLoading = true) }

        try {
            if (_uiState.value.user?.role == RoleEnum.PATIENT) {
                _uiStateForm.update {
                    it.copy(
                        firstName = _uiState.value.patient?.firstName,
                        lastName = _uiState.value.patient?.lastName,
                        middleName = _uiState.value.patient?.middleName,
                        dateOfBirth = _uiState.value.patient?.dateOfBirth,
                        gender = _uiState.value.patient?.gender,
                        phoneNumber = _uiState.value.patient?.phoneNumber,
                        isLoading = false
                    )
                }
            } else if(_uiState.value.user?.role == RoleEnum.DOCTOR) {
                _uiStateForm.update {
                    it.copy(
                        firstName = _uiState.value.doctor?.firstName,
                        lastName = _uiState.value.doctor?.lastName,
                        middleName = _uiState.value.doctor?.middleName,
                        phoneNumber = _uiState.value.doctor?.phoneNumber,
                        isLoading = false
                    )
                }
            }
            _uiState.update { it.copy(isLoading = false) }
        } catch (e: Exception){
            _uiStateForm.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateInfo() {
        try {
            _uiStateForm.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                if(_uiState.value.user?.role == RoleEnum.PATIENT) {
                    apiService.updatePatientFullInfo(
                        id = _uiState.value.patient?.id!!,
                        patient = UpdatePatientFullInformationRequest(
                            firstName = _uiStateForm.value.firstName ?: "Нет данных",
                            lastName = _uiStateForm.value.lastName ?: "Нет данных",
                            middleName = _uiStateForm.value.middleName ?: "Нет данных",
                            dateOfBirth = _uiStateForm.value.dateOfBirth!!,
                            gender = _uiStateForm.value.gender ?: "Нет данных",
                            phoneNumber = _uiStateForm.value.phoneNumber ?: "Нет данных",
                        )
                    )
                    _uiStateForm.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }

                } else if (_uiState.value.user?.role == RoleEnum.DOCTOR) {
                    apiService.updateDoctorFullInfo(
                        id = _uiState.value.doctor?.id!!,
                        doctor = UpdateDoctorFullInformationRequest(
                            firstName = _uiStateForm.value.firstName ?: "Нет данных",
                            lastName = _uiStateForm.value.lastName ?: "Нет данных",
                            middleName = _uiStateForm.value.middleName ?: "Нет данных",
                            phoneNumber = _uiStateForm.value.phoneNumber ?: "Нет данных"
                        )
                    )
                    _uiStateForm.update {
                        it.copy(
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        } catch (e: Exception) {
            _uiStateForm.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    suspend fun loadStatsPatient() {
        try {
            _uiState.update { it.copy(isLoadingStats = true) }

            val patient = apiService.getFullInformationPatientByUsername(_uiState.value.user?.username!!)
            val stats = apiService.getProfileStatsPatient(patient.id)

            _uiState.update {
                it.copy(
                    countAppointment = stats.countAppointment,
                    dateRegister = stats.dateOfRegistration,
                    countCurrentAppointment = stats.countCurrentAppointment,
                    patient = patient,
                    isLoadingStats = false
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoadingStats = false,
                    error = e.message
                )
            }
        }
    }
    suspend fun loadStatsDoctor() {
        try {
            _uiState.update { it.copy(isLoadingStats = true) }

            val doctor = apiService.getFullInformationDoctorByUsername(_uiState.value.user?.username!!)
            val stats = apiService.getProfileStatsDoctor(doctor.id)

            _uiState.update {
                it.copy(
                    countAppointment = stats.countAppointment,
                    dateRegister = stats.dateOfRegistration,
                    countCurrentAppointment = stats.countCurrentAppointment,
                    doctor = doctor,
                    isLoadingStats = false
                )
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoadingStats = false,
                    error = e.message
                )
            }
        }
    }
}