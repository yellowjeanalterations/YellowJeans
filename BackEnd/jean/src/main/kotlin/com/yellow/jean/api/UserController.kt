package com.yellow.jean.api

import com.yellow.jean.domain.BaseResponseBody
import com.yellow.jean.domain.UserDto
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.error.exception.ErrorCode
import com.yellow.jean.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userDetailsService: UserDetailsService,
    private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/info")
    @Operation(summary = "회원가입", description = "회원 정보 등록", tags = ["UserController"])
    @ApiResponse(responseCode = "200", description = "정상")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<BaseResponseBody> {
        userDto.password = passwordEncoder.encode(userDto.password)
        userService.insertUser(userDto)
        return ResponseEntity.ok(BaseResponseBody(200, "OK"))
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "토큰 발급", tags = ["UserController"])
    @ApiResponse(responseCode = "200", description = "정상")
    fun login(@RequestBody userDto: UserDto): ResponseEntity<String> {
        val user: UserDetails = userDetailsService.loadUserByUsername(userDto.email)
        if (!passwordEncoder.matches(
                userDto.password,
                user.password
            )
        ) throw BusinessException(ErrorCode.INVALID_PASSWORD)
        return ResponseEntity.ok(userService.login(userDto))
    }

}