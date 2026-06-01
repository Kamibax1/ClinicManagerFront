package com.example.clinicmanagerfront.presentation.view.adminPanelScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.data.model.CreateUserModel
import com.example.clinicmanagerfront.data.model.UserResponse
import com.example.clinicmanagerfront.data.model.enums.RoleEnum
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiEvent.AdminPanelUiEvent
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState.AdminPanelUiState
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.uiState.CreateUserFormUiState
import com.example.clinicmanagerfront.presentation.view.adminPanelScreen.userCard.UserDataCard
import com.example.clinicmanagerfront.ui.theme.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class AdminPanelViewModel @Inject constructor(
    val apiService: ApiService
) : ViewModel() {
    private val _uiState = MutableStateFlow(AdminPanelUiState())
    val uiState: StateFlow<AdminPanelUiState> = _uiState.asStateFlow()

    private val _uiStateForm = MutableStateFlow(CreateUserFormUiState())
    val uiStateForm: StateFlow<CreateUserFormUiState> = _uiStateForm.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadUsers()
    }

    fun postUiEvent(event: AdminPanelUiEvent) {
        when(event) {
            is AdminPanelUiEvent.SearchUser -> searchUsers(event.partUsername)
            is AdminPanelUiEvent.UpdateUserUsername -> updateUserUsername(event.username)
            is AdminPanelUiEvent.UpdateUserPassword -> updateUserPassword(event.password)
            is AdminPanelUiEvent.UpdateUserEmail -> updateUserEmail(event.email)
            is AdminPanelUiEvent.UpdateUserEnable -> updateUserEnabled(event.id)
            is AdminPanelUiEvent.UpdateUserRoleById -> updateUserRoleById(event.id, event.role)
            is AdminPanelUiEvent.UpdateUserRole -> updateUserRole(event.role)
            is AdminPanelUiEvent.FilterUsers -> filterUsers(event.filterType)
            is AdminPanelUiEvent.UpdateSearchActive -> updateSearchActive()
            is AdminPanelUiEvent.UpdateFormActive -> updateFormActive()
            is AdminPanelUiEvent.UpdateAddUserFormActive -> updateAddUserFormActive()
            is AdminPanelUiEvent.OnConfirm -> createUser()
        }
    }

    fun loadUsers() {
        try {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                val users = apiService.getAllUsers()
                val cards = users.map { user -> mapUserToCard(user) }
                _uiState.update {
                    it.copy(
                        users = users,
                        cards = cards,
                        filteredCards = cards,
                        isLoading = false,
                        error = null
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun mapUserToCard(user: UserResponse) : UserDataCard {
        try {
            val enabled = if (user.enabled) "Активен" else "Заблокирован"
            val buttonText = if (user.enabled) "Заблокировать" else "Разблокировать"
            val buttonColor = if (user.enabled) Red600 else Green700
            return UserDataCard(
                id = user.id,
                username = user.username,
                email = user.username,
                enabled = enabled,
                role = user.role.ru,
                buttonText = buttonText,
                buttonColor = buttonColor
            )
        } catch (e: Exception) {
            Log.e("Mapping", e.message ?: "Ошибка маппинга")
            throw Exception()
        }
    }

    fun updateSearchActive() {
        _uiState.update { it.copy(searchActive = !it.searchActive) }
    }

    fun updateFormActive() {
        _uiState.update { it.copy(showFilterMenu = !it.showFilterMenu) }
    }

    fun updateAddUserFormActive() {
        _uiState.update { it.copy(showAddUserForm = !it.showAddUserForm) }
    }

    fun searchUsers(partUsername: String) {
        _uiState.update { it.copy(searchText = partUsername) }
        searchJob?.cancel()

        if (partUsername.isBlank()) {
            _uiState.update { it.copy(filteredCards = it.cards) }
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            _uiState.update { it.copy(isLoading = true) }
            try {
                val users = try { apiService.getAllUsersByPartUsername(partUsername) }
                catch (e: HttpException) { if (e.code() == 404) emptyList() else throw e }

                val cards = users.map { mapUserToCard(it) }
                _uiState.update {
                    it.copy(
                        filteredCards = cards,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun updateUserEnabled(id: Long) {
        try {
            _uiState.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                val user = apiService.updateUserEnabled(id)
                val enabled = if (user.enabled) "Активен" else "Заблокирован"
                val buttonText = if (user.enabled) "Заблокировать" else "Разблокировать"
                val buttonColor = if (user.enabled) Red600 else Green700

                val updatedCards = _uiState.value.filteredCards.map { card ->
                    if (card.id == id) {
                        card.copy(
                            enabled = enabled,
                            buttonText = buttonText,
                            buttonColor = buttonColor
                        )
                    } else {
                        card
                    }
                }

                _uiState.update {
                    it.copy(
                        filteredCards = updatedCards,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateUserRoleById(id: Long, role: RoleEnum) {
        try {
            _uiState.update { it.copy(isLoading = true) }

            viewModelScope.launch {
                apiService.updateUserRole(id, role)
                val updatedCards = _uiState.value.filteredCards.map { card ->
                    if (card.id == id) {
                        card.copy(
                            role = role.ru
                        )
                    } else {
                        card
                    }
                }

                _uiState.update {
                    it.copy(
                        filteredCards = updatedCards,
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun filterUsers(filterType: FilterType) {
        try {
            _uiState.update { it.copy(isLoading = true) }
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        filteredCards = when(filterType) {
                            FilterType.ALL -> { it.cards }
                            FilterType.USERNAME -> {
                                try {
                                    apiService.getAllUsersByOrderUsername().map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.EMAIL -> {
                                try {
                                    apiService.getAllUsersByOrderEmail().map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.ACTIVE -> {
                                try {
                                    apiService.getAllUsersByEnabled(true).map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.BLOCKED -> {
                                try {
                                    apiService.getAllUsersByEnabled(false).map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.DOCTOR -> {
                                try {
                                    apiService.getAllUsersByRoleName(RoleEnum.DOCTOR).map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.PATIENT -> {
                                try {
                                    apiService.getAllUsersByRoleName(RoleEnum.PATIENT).map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                            FilterType.ADMIN -> {
                                try {
                                    apiService.getAllUsersByRoleName(RoleEnum.ADMIN).map { user -> mapUserToCard(user) }
                                } catch (e: HttpException) {
                                    if (e.code() == 404) emptyList() else throw e
                                }
                            }
                        },
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun updateUserUsername(username: String) {
        _uiStateForm.update { it.copy(username = username) }
    }

    fun updateUserPassword(password: String) {
        _uiStateForm.update { it.copy(password = password) }
    }

    fun updateUserEmail(email: String) {
        _uiStateForm.update { it.copy(email = email) }
    }

    fun updateUserRole(role: RoleEnum) {
        _uiStateForm.update { it.copy(role = role) }
    }

    fun createUser() {
        _uiStateForm.update { it.copy(isLoading = true) }

        if (
            _uiStateForm.value.username.isNullOrBlank() ||
            _uiStateForm.value.password.isNullOrBlank() ||
            _uiStateForm.value.email.isNullOrBlank() ||
            _uiStateForm.value.role == null
        ) {
            _uiStateForm.update {
                it.copy(
                    isLoading = false,
                    error = "Ошибка. Не все поля заполнены"
                )
            }
            return
        }

        viewModelScope.launch {
            try {
                apiService.saveUser(
                    CreateUserModel(
                        username = _uiStateForm.value.username!!,
                        password = _uiStateForm.value.password!!,
                        email = _uiStateForm.value.email!!,
                        enabled = true,
                        role = _uiStateForm.value.role!!
                    )
                )
                _uiStateForm.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        username = "",
                        password = "",
                        email = "",
                        role = null
                    )
                }
                _uiState.update { it.copy(showAddUserForm = false) }
                loadUsers()
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    400 -> "Неверные данные. Проверьте правильность заполнения полей"
                    409 -> "Пользователь с таким именем или email уже существует"
                    500 -> "Внутренняя ошибка сервера"
                    else -> "Ошибка сервера: ${e.message()}"
                }
                _uiStateForm.update {
                    it.copy(
                        isLoading = false,
                        error = errorMessage
                    )
                }
            } catch (e: Exception) {
                _uiStateForm.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Неизвестная ошибка"
                    )
                }
            }
        }
    }
}
