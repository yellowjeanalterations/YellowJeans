package com.yellow.jean.service

import com.yellow.jean.domain.UserDto
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.error.exception.ErrorCode
import com.yellow.jean.repository.UserRepository
import com.yellow.jean.util.JwtTokenProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
){
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)
    fun insertUser(user: UserDto){
        log.info(user.toEntity().toString())
        userRepository.save(user.toEntity())
    }
    fun login(user: UserDto): String {
        return user.email?.let { jwtTokenProvider.createToken(it) } ?: throw BusinessException(ErrorCode.NOT_NULL)
    }
}