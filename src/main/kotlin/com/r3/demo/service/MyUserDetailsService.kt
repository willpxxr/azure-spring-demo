package com.r3.demo.service

import com.r3.demo.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        LoggerFactory.getLogger(this.javaClass).warn("Attempting to get load user with username $username")
        val user = userRepository.findByName(username!!)
        val authorities: Set<GrantedAuthority> = setOf(
                SimpleGrantedAuthority(user.role.toString())
        )

        return org.springframework.security.core.userdetails.User(
                user.name,
                user.password,
                authorities
        )

    }
}