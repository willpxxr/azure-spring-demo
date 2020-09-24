package com.r3.demo.config

import com.r3.demo.model.Role
import com.r3.demo.model.UserData
import com.r3.demo.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class Bootstrap: ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private lateinit var userRepository: UserRepository

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        logger.info("Verifying test user exists")
        createUser("admin", "password", Role.ROLE_ADMIN)
        createUser("wparr", "password", Role.ROLE_USER)
    }

    private fun createUser(
            username: String,
            password: String,
            role: Role
    ){
        userRepository.save(
                UserData(
                        name = username,
                        password = BCryptPasswordEncoder().encode(password),
                        role = role,
                        enabled = true
                    )
            )
    }

}