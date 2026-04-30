package com.example.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.data.UserRepository
import com.example.app.domain.model.User
import com.example.app.ui.home.HomeViewModel
import com.example.app.utils.FakeUserRepository
import com.example.app.utils.createUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.MainDispatcherRule
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class HomeViewModelTest {

    @RegisterExtension
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var fakeRepository: FakeUserRepository

    @BeforeEach
    fun setUp() {
        fakeRepository = FakeUserRepository()
        viewModel = HomeViewModel(fakeRepository)
    }

    @Test
    fun `loadUser updates uiState to success`() = runTest {
        val user = createUser(id = "1")
        fakeRepository.saveUser(user)

        viewModel.loadUser("1")
        advanceUntilIdle()

        assertThat(
            viewModel.uiState.value,
            instanceOf(UiState.Success::class.java)
        )
    }

    @Test
    fun `loadUser updates uiState to error when repository fails`() = runTest {
        fakeRepository.shouldThrowError = true

        viewModel.loadUser("nonexistent")
        advanceUntilIdle()

        assertThat(
            viewModel.uiState.value,
            instanceOf(UiState.Error::class.java)
        )
    }
}
