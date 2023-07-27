package com.yellow.jean.service

import com.yellow.jean.domain.UserDto
import org.junit.jupiter.api.Test

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService

@SpringBootTest
class UserServiceTest {
    val log: Logger = LoggerFactory.getLogger(UserServiceTest::class.java)
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var userDetailsService: UserDetailsService
    @Test
    fun insertUser() {
        for( i in 1..200)
            userService.insertUser(UserDto("${i}_이성무","sm-lee$i@nate.com","password", null))
        for( i in 1..200){
            assert(userDetailsService.loadUserByUsername("sm-lee$i@nate.com").username == "sm-lee$i@nate.com")
        }
    }

    @Test
    fun login() {
        val userDto = UserDto("이성무", "sm-lee@nate.com", "password", null)
        userService.insertUser(userDto)
        log.info(userService.login(userDto))
    }
}