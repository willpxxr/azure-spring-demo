package com.r3.demo.controller

import com.r3.demo.model.Role
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SecureMessagesController {

    @RequestMapping("/secure")
    fun displaySecret(@AuthenticationPrincipal principal: UserDetails): String {
        return "$principal"
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/secure/user")
    fun displayTestRoleSpecificSecret(): String {
        return "${Role.ROLE_USER} secret"
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/secure/admin")
    fun displayTestAdminSpecificSecret(): String {
        return "${Role.ROLE_ADMIN} secret"
    }

}