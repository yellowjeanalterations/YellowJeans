package com.yellow.jean.domain

import com.yellow.jean.domain.enums.AuthProvider
import com.yellow.jean.domain.enums.Authority
import com.yellow.jean.entity.User
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.domain.enums.ErrorCode
import org.springframework.security.core.GrantedAuthority
/**
 * packageName    : com.yellow.jean.domain
 * fileName       : UserDto
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 유저 Dto 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
data class UserDto(
    var id: Long?,
    var name: String,
    val height: Double?,
    val weight: Double?,
    val bust: Double?,
    val hip: Double?,
    val thigh: Double?,
    val waist: Double?,
    val extId: String,
    val extToken: String?,
    val authProvider: AuthProvider,
    var authority: Authority?
) {
    fun toEntity(): User {
        if (name == null || height == null || weight == null || bust == null || hip == null || thigh == null || waist == null || extId == null || extToken == null || authProvider == null)
            throw BusinessException(ErrorCode.NOT_NULL)
        return User(
            name = name,
            height = height,
            weight = weight,
            authority = authority ?: Authority.USER,
            bust = bust,
            hip = hip,
            thigh = thigh,
            waist = waist,
            authProvider = authProvider,
            extId = extId,
            extToken = extToken
        )
    }

    fun getAuthority(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(GrantedAuthority { authority?.name })
    }
}