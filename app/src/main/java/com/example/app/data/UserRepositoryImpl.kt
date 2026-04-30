package com.example.app.data

import com.example.app.domain.model.User

class UserRepositoryImpl(
    private val dataSource: UserDataSource
) : UserRepository {

    override suspend fun fetchUser(id: String): Result<User> = try {
        Result.success(dataSource.getUser(id))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun saveUser(user: User): Result<Boolean> = try {
        Result.success(dataSource.saveUser(user))
    } catch (e: Exception) {
        Result.failure(e)
    }
}
