package com.r3.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.jws.WebParam

@Controller
class LoginController {

    @RequestMapping("/login")
    fun displayLogin(): ModelAndView {
        return ModelAndView("login/login")
    }

}