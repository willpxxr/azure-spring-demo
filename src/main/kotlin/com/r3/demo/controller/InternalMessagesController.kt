package com.r3.demo.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class InternalMessagesController {

    @RequestMapping("/internal/")
    @PreAuthorize("hasRole('prod-se-owner')")
    fun displayInternalMessagesIndex(): String {
        return "Something only internal AD users can see"
    }

    @RequestMapping("internal/user")
    fun getUserInfo(@AuthenticationPrincipal user: Principal): String {
        return "$user"
    }
}
