package com.example.clinicmanagerfront.presentation.view.profileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clinicmanagerfront.data.api.ApiService
import com.example.clinicmanagerfront.presentation.view.profileScreen.uiState.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {

    }

//    fun loadProfile() {
//        viewModelScope.launch {
//            _uiState.update { it.copy(isLoading = true) }
//
//            try {
//                _uiState.update {
//                    it.copy(
//                        name
//                    )
//                }
//            }
//        }
//    }
}