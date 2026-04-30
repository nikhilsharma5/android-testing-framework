package com.example.app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app.domain.model.User
import com.example.app.ui.UiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    user: User? = null,
    onLoadClick: () -> Unit = { viewModel.loadUser("1") }
) {
    val uiState = viewModel.uiState.observeAsState(initial = UiState.Loading).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator()
            }
            is UiState.Success -> {
                Text("Name: ${uiState.user.name}")
                Text("Email: ${uiState.user.email}")
            }
            is UiState.Error -> {
                Text("Error: ${uiState.message}")
            }
        }

        Button(
            onClick = onLoadClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Load Profile")
        }
    }
}
