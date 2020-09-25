package com.r3.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController {
    @RequestMapping("/")
    fun displayIndex(): String {
        return "index"
    }
}
