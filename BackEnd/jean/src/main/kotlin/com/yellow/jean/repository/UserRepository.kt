package com.yellow.jean.repository

import com.yellow.jean.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(email: String): User?
}