package com.yellow.jean.service

import com.yellow.jean.domain.UserDetailsImpl
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.domain.enums.ErrorCode
import com.yellow.jean.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
/**
 * packageName    : com.yellow.jean.service
 * fileName       : UserDetailsServiceImpl
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : Spring Security 인증 기본 서비스 객체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Service
class UserDetailsServiceImpl: UserDetailsService {
    private val log: Logger = LoggerFactory.getLogger(UserDetailsService::class.java)
    @Autowired
    private lateinit var userRepository: UserRepository
    override fun loadUserByUsername(username: String?): UserDetails {
        username ?: throw BusinessException(ErrorCode.NOT_NULL)
        return username.run{
            val user = userRepository.findByExtId(this) ?: throw BusinessException(ErrorCode.EXT_ID_NOT_FOUND)
            UserDetailsImpl(user.toDomain())
        }
    }
}