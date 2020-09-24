package com.r3.demo.controller

//import org.springframework.security.access.prepost.PreAuthorize
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//class HelloController {
//    @RequestMapping("/")
//    fun index() = "Navigate to /secure/secret for secret content"
//
//    @PreAuthorize("hasRole('prod-se-contrib')")
//    @RequestMapping("/secure/secret")
//    fun securedMessage(): String {
//        return "My Secret"
//    }
//
//    @PreAuthorize("hasRole('not-a-real-role')")
//    @RequestMapping("/secure/inaccessible")
//    fun inaccessibleMessage(): String {
//        return "Hopefully no one can see this"
//    }
//}
