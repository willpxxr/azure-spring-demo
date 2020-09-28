package com.r3.demo.config

import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.HttpSecurityBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * Extension method to ExpressionUrlAuthorizationConfigurer, to enable IpAddress restriction from
 * a collection of strings representing IP addresses.
 */
fun <T : HttpSecurityBuilder<T>?> ExpressionUrlAuthorizationConfigurer<T>.AuthorizedUrl.hasIpAddress(collection: Collection<String>): ExpressionUrlAuthorizationConfigurer<T>.ExpressionInterceptUrlRegistry {
    return access(
            collection.joinToString(separator = " or ") {
                "hasIpAddress('$it')"
            }
    )
}

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig: WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    private lateinit var ipConfig: IpConfig

    override fun configure(http: HttpSecurity?) {
        LogFactory.getLog(javaClass).warn(ipConfig.whitelist)

        http!!.authorizeRequests()
                .antMatchers("/")
                    .permitAll()
                .antMatchers("/api/**")
                    .hasIpAddress(ipConfig.whitelist)
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
