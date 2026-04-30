package com.example.app.utils

import com.example.app.domain.model.User

fun createUser(
    id: String = "1",
    name: String = "Test User",
    email: String = "test@example.com"
) = User(id, name, email)

class UserBuilder {
    private var id = "1"
    private var name = "Test User"
    private var email = "test@example.com"

    fun withId(id: String) = apply { this.id = id }
    fun withName(name: String) = apply { this.name = name }
    fun withEmail(email: String) = apply { this.email = email }

    fun build() = User(id, name, email)
}

fun userBuilder() = UserBuilder()
