package com.yellow.jean.service

import com.yellow.jean.domain.KakaoLoginParam
import com.yellow.jean.domain.OAuthLoginParam
import com.yellow.jean.domain.UserDto
import com.yellow.jean.domain.enums.AuthProvider
import com.yellow.jean.domain.enums.ErrorCode
import com.yellow.jean.entity.User
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.repository.UserRepository
import com.yellow.jean.util.JwtTokenProvider
import com.yellow.jean.util.KakaoApiClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
/**
 * packageName    : com.yellow.jean.service
 * fileName       : UserService
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 유저 관련 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val kakaoApiClient: KakaoApiClient,
){
    private val log: Logger = LoggerFactory.getLogger(UserService::class.java)
    fun updateUser(user: UserDto){
        log.info(user.toEntity().toString())
        val findUserEntity = user.id?.let { userRepository.findById(it).orElse(null) } ?: throw BusinessException(ErrorCode.NOT_NULL)
        findUserEntity.bodySizeSetting(user)
    }
    fun login(oAuthLoginParam: OAuthLoginParam): String {
        return when(oAuthLoginParam){
            is KakaoLoginParam -> kakaoLogin(oAuthLoginParam)
            else -> throw BusinessException(ErrorCode.EXT_ID_NOT_FOUND)
        }.extId.let { jwtTokenProvider.createToken(it)  }
    }
    fun kakaoLogin(kakaoLoginParam: KakaoLoginParam): UserDto {
        val kaKaoLoginResponse = kakaoApiClient.requestAccessToken(kakaoLoginParam) ?: throw BusinessException(ErrorCode.NOT_NULL)
        log.info(kaKaoLoginResponse.toString())
        return userRepository.save(User(name= "", extToken = kaKaoLoginResponse.accessToken, extId = kaKaoLoginResponse.resolveToken(), authProvider = AuthProvider.KAKAO)).toDomain()
    }
}