package com.yellow.jean.domain

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.yellow.jean.domain.enums.ErrorCode
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.util.UnixTimestampDeserializer
import io.jsonwebtoken.Jwts
import java.time.LocalDateTime
import java.util.*

/**
 * packageName    : com.yellow.jean.domain
 * fileName       : KaKaoLoginResponse
 * author         : iseongmu
 * date           : 2023/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/15        iseongmu       최초 생성
 */
data class KaKaoLoginResponse(
    @JsonAlias("token_type")
    val tokenType: String,
    @JsonAlias("access_token")
    val accessToken: String,
    @JsonAlias("id_token")
    val idToken: String,
    @JsonAlias("expires_in")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val expiresIn: LocalDateTime,
    @JsonAlias("refresh_token")
    val refreshToken: String,
    @JsonAlias("refresh_token_expires_in")
    @JsonDeserialize(using = UnixTimestampDeserializer::class)
    val refreshTokenExpiresIn: LocalDateTime,
    val scope: String,
){
    fun resolveToken(): String{
        try {
           return Jwts.parser()
                .parseClaimsJws(Base64.getDecoder().decode(idToken).toString()).body.subject
        } catch (e: Exception) {
            println(e.message)
            throw BusinessException(ErrorCode.DEFAULT_ERROR)
        }
    }
}