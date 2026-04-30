package com.example.app.utils

import com.example.app.data.UserRepository
import com.example.app.domain.model.User
import kotlinx.coroutines.delay

class FakeUserRepository : UserRepository {
    var shouldThrowError = false
    var delayMs = 0L

    private val users = mutableMapOf<String, User>()

    override suspend fun fetchUser(id: String): Result<User> {
        delay(delayMs)
        return if (shouldThrowError) {
            Result.failure(Exception("Simulated network error"))
        } else {
            users[id]?.let { Result.success(it) }
                ?: Result.failure(Exception("User not found"))
        }
    }

    override suspend fun saveUser(user: User): Result<Boolean> {
        delay(delayMs)
        return if (shouldThrowError) {
            Result.failure(Exception("Failed to save user"))
        } else {
            users[user.id] = user
            Result.success(true)
        }
    }
}
