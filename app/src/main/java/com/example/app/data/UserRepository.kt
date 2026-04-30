package com.example.app.data

import com.example.app.domain.model.User

interface UserRepository {
    suspend fun fetchUser(id: String): Result<User>
    suspend fun saveUser(user: User): Result<Boolean>
}
