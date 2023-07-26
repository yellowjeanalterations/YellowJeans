package com.yellow.jean

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class JeanApplication

fun main(args: Array<String>) {
    runApplication<JeanApplication>(*args)
}
