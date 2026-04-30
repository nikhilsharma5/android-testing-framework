package com.example.app.data

import com.example.app.domain.model.User

class UserDataSourceImpl : UserDataSource {
    private val users = mutableMapOf<String, User>()

    override suspend fun getUser(id: String): User {
        return users[id] ?: throw Exception("User not found")
    }

    override suspend fun saveUser(user: User): Boolean {
        users[user.id] = user
        return true
    }
}
