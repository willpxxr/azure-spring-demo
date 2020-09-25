package com.r3.demo.controller

import com.r3.demo.model.Role
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class SecureMessagesController {

    @RequestMapping("/secure")
    fun displaySecret(@AuthenticationPrincipal principal: Principal): String {
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
