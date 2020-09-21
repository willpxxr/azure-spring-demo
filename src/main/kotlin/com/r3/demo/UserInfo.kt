package com.r3.demo

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfo {
    @RequestMapping("/user")
    @ResponseBody
    fun getUserInfo(@AuthenticationPrincipal user: OAuth2User): String {
        return "Name: ${user.name}\n" +
                "Authorities: ${user.authorities}\n" +
                "Attributes: ${user.attributes}"
    }
}