package com.r3.demo.repository

import com.r3.demo.model.UserData
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserData, Long> {
    fun findByName(name: String): UserData
    fun save(user: UserData): UserData
}