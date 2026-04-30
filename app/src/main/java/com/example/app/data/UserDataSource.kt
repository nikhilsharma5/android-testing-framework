package com.example.app.data

import com.example.app.domain.model.User

interface UserDataSource {
    suspend fun getUser(id: String): User
    suspend fun saveUser(user: User): Boolean
}
