package com.r3.demo.controller

import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class LoginController {
    @Autowired
    private lateinit var clientProperties: OAuth2ClientProperties

    private val authorizationRequestBaseUri = "oauth2/authorization"

    @RequestMapping("/login")
    fun login(model: Model): String {
        val logger = LogFactory.getLog(javaClass)

        // TODO: Is there a more elegant way to do this?
        val providerUrlMap = clientProperties.registration.mapValues {
            "$authorizationRequestBaseUri/${it.key}"
        }

        logger.info(providerUrlMap)

        model.addAttribute("urls", providerUrlMap)

        return "login"
    }
}