package com.r3.demo.config

import com.r3.demo.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService: MyUserDetailsService

    override fun configure(http: HttpSecurity?) {
        http!!.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }

}

/**
 * TODO: Need to figure out how to combine Azure AD OAuth, and form auth against DB
 */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class WebSecurityConfig: WebSecurityConfigurerAdapter() {
//    @Override
//    override fun configure(http: HttpSecurity?) {

//        http!!.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/").permitAll() // Permit unauthenticated access to the index
//                .anyRequest().authenticated() // Any other request requires authentication
//                .and().oauth2Login()
//    }
//}