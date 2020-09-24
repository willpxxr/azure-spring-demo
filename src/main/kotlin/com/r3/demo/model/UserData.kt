package com.r3.demo.model

import java.util.*
import javax.persistence.*

@Entity
data class UserData(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,

        @Column(nullable = true)//, unique = true)
        val name: String = "",

        @Column(nullable = true)
        val password: String = "",

        @Column(nullable = true)
        @Enumerated(EnumType.STRING)
        val role: Role,

        @Column(nullable = true)
        val enabled: Boolean

        //@Column(nullable = false)
        //@Temporal(TemporalType.TIMESTAMP)
        //val dataCreated: Date = Date()
)
