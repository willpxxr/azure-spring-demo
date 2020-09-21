package com.r3.demo

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    @Override
    override fun configure(http: HttpSecurity?) {

        http!!.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/").permitAll() // Permit unauthenticated access to the index
                .anyRequest().authenticated() // Any other request requires authentication
                .and().oauth2Login()
    }
}
