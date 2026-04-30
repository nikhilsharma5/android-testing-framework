package com.example.app.ui

import com.example.app.domain.model.User

sealed class UiState {
    object Loading : UiState()
    data class Success(val user: User) : UiState()
    data class Error(val message: String) : UiState()
}
