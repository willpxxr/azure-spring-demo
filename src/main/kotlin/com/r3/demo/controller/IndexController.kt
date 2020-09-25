package com.r3.demo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController {
    @RequestMapping("/")
    fun displayIndex(): String {
        return "Hello World"
    }
}
