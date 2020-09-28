package com.r3.demo.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ConfigurationProperties(prefix = "ip")
@PropertySource(value = ["classpath:/config.properties"])
class IpConfig {
    lateinit var whitelist: Set<String>
}