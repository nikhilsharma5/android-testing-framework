package com.example.app.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.app.domain.model.User
import com.example.app.ui.home.HomeScreen
import com.example.app.utils.createUser
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `displays user name when in success state`() {
        val user = createUser(name = "John Doe")

        composeTestRule.setContent {
            HomeScreen(
                user = user,
                onLoadClick = {}
            )
        }

        composeTestRule
            .onNodeWithText("Name: John Doe")
            .assertIsDisplayed()
    }

    @Test
    fun `displays load button`() {
        composeTestRule.setContent {
            HomeScreen(onLoadClick = {})
        }

        composeTestRule
            .onNodeWithText("Load Profile")
            .assertIsDisplayed()
    }

    @Test
    fun `clicking load button triggers callback`() {
        var clickCount = 0

        composeTestRule.setContent {
            HomeScreen(onLoadClick = { clickCount++ })
        }

        composeTestRule
            .onNodeWithText("Load Profile")
            .performClick()

        assert(clickCount == 1)
    }
}
