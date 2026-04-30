package com.example.app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.UserRepository
import com.example.app.ui.UiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>(UiState.Loading)
    val uiState: LiveData<UiState> = _uiState

    fun loadUser(userId: String) {
        viewModelScope.launch {
            val result = repository.fetchUser(userId)
            _uiState.value = result
                .fold(
                    onSuccess = { user -> UiState.Success(user) },
                    onFailure = { error -> UiState.Error(error.message ?: "Unknown error") }
                )
        }
    }
}
