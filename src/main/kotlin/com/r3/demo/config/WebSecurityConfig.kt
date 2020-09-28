package com.r3.demo.config

import com.r3.demo.service.MyUserDetailsService
import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    private val whiteListedIps: Set<String> = setOf(
            "127.0.0.1",
            "255.1.1.1"
    )

    override fun configure(http: HttpSecurity?) {
        val ipAddressWhiteListString: String =
                whiteListedIps.joinToString(separator = " or ") {
                    "hasIpAddress('$it')"
                }

        LogFactory.getLog(javaClass).warn(
                ipAddressWhiteListString
        )

        http!!.authorizeRequests()
                .antMatchers("/")
                    .permitAll()
                .antMatchers("/api/**").access(ipAddressWhiteListString)
                .anyRequest()
                    .authenticated()
                .and()
                    .oauth2Login {
                        it.loginPage("/login").permitAll()
                    }
                    .formLogin {
                        it.loginPage("/login").permitAll()
                    }
    }
}
