package com.example.app.integration

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.app.data.UserDataSourceImpl
import com.example.app.data.UserRepositoryImpl
import com.example.app.ui.home.HomeScreen
import com.example.app.ui.home.HomeViewModel
import com.example.app.utils.createUser
import org.junit.Rule
import org.junit.Test

class UserJourneyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `user can load profile and see user details`() {
        val dataSource = UserDataSourceImpl()
        val testUser = createUser(id = "1", name = "Alice Smith", email = "alice@example.com")
        dataSource.saveUser(testUser)

        val repository = UserRepositoryImpl(dataSource)
        val viewModel = HomeViewModel(repository)

        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }

        // User clicks load button
        composeTestRule
            .onNodeWithText("Load Profile")
            .performClick()

        // Wait for result and verify user details are shown
        composeTestRule
            .onNodeWithText("Name: Alice Smith")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Email: alice@example.com")
            .assertIsDisplayed()
    }
}
