package com.r3.demo

import com.nimbusds.openid.connect.sdk.UserInfoRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.oidc.user.OidcUser

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var oidcUserService: OAuth2UserService<OidcUserRequest, OidcUser>

    @Override
    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers("/secure/**")
                .authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService)
    }



}
