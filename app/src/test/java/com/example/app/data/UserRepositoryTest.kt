package com.example.app.data

import com.example.app.utils.createUser
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryTest {

    private lateinit var repository: UserRepository
    private lateinit var dataSource: UserDataSource

    @BeforeEach
    fun setUp() {
        dataSource = UserDataSourceImpl()
        repository = UserRepositoryImpl(dataSource)
    }

    @Test
    fun `fetchUser returns success when user exists`() = runTest {
        val user = createUser(id = "1", name = "Alice")
        dataSource.saveUser(user)

        val result = repository.fetchUser("1")

        assertThat(result.isSuccess, `is`(true))
        assertThat(result.getOrNull()?.name, `is`("Alice"))
    }

    @Test
    fun `fetchUser returns failure when user does not exist`() = runTest {
        val result = repository.fetchUser("nonexistent")

        assertThat(result.isFailure, `is`(true))
    }

    @Test
    fun `saveUser stores user and returns success`() = runTest {
        val user = createUser(id = "2", name = "Bob")

        val result = repository.saveUser(user)

        assertThat(result.isSuccess, `is`(true))
        assertThat(result.getOrNull(), `is`(true))
    }
}
