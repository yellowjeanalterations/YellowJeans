package com.yellow.jean.api

import com.yellow.jean.domain.BaseResponseBody
import com.yellow.jean.domain.KakaoLoginParam
import com.yellow.jean.domain.UserDetailsImpl
import com.yellow.jean.domain.UserDto
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.domain.enums.ErrorCode
import com.yellow.jean.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
/**
 * packageName    : com.yellow.jean.api
 * fileName       : UserController
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 유저 관련 컨트롤러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
) {
    @PutMapping("/body")
    @Operation(summary = "신체 데이터 입력", description = "최초 가입시 신체 데이터 입력", tags = ["UserController"])
    @ApiResponse(responseCode = "200", description = "정상")
    fun register(@RequestBody userDto: UserDto, authentication: Authentication): ResponseEntity<BaseResponseBody> {
        val principal = authentication.principal as UserDetailsImpl
        userDto.id = principal.user.id
        userService.updateUser(userDto)
        return ResponseEntity.ok(BaseResponseBody(200, "OK"))
    }

    @PostMapping("/login/kakao")
    @Operation(summary = "로그인", description = "토큰 발급", tags = ["UserController"])
    @ApiResponse(responseCode = "200", description = "정상")
    fun login(@RequestBody kakaoLoginParam: KakaoLoginParam): ResponseEntity<String> {
        return ResponseEntity.ok(userService.login(kakaoLoginParam))
    }

}