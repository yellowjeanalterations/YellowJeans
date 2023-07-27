package com.yellow.jean.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(private val userDetailsService: UserDetailsService) {
    // JWT를 생성하고 검증하는 컴포넌트
    private var secretKey = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="
    private val TOKEN_PREFIX = "Bearer "
    private val HEADER_STRING = "Authorization"
    // 토큰 유효시간 30분
    private val tokenValidTime = 30 * 60 * 1000L

    // JWT 토큰 생성
    fun createToken(userPk: String): String {
        val claims: Claims = Jwts.claims().setSubject(userPk) // JWT payload 에 저장되는 정보단위
        claims["userPk"] = userPk // 정보는 key / value 쌍으로 저장된다.
        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.getDecoder().decode(secretKey)
            )
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenValidTime))
            .compact()
    }

    // JWT 토큰에서 인증 정보 조회
    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUserPk(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰에서 회원 정보 추출
    fun getUserPk(token: String): String {
        return Jwts.parser()
            .setSigningKey(Base64.getDecoder().decode(secretKey))
            .parseClaimsJws(token).body.subject
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader(HEADER_STRING)?.replace(TOKEN_PREFIX,"")
    }

    // 토큰의 유효성 + 만료일자 확인
    fun validateToken(jwtToken: String): Boolean {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .parseClaimsJws(jwtToken)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}