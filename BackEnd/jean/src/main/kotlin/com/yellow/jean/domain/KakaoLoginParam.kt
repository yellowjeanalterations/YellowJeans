package com.yellow.jean.domain

import com.yellow.jean.domain.enums.AuthProvider
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap


/**
 * packageName    : com.yellow.jean.domain
 * fileName       : KakaoLoginParam
 * author         : iseongmu
 * date           : 2023/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
class KakaoLoginParam(
    private val authorizationCode: String,
    private val redirectUri: String,
): OAuthLoginParam {
    override fun authProvider(): AuthProvider {
        return AuthProvider.KAKAO
    }
    override fun makeBody(): MultiValueMap<String, String> {
        val body: MultiValueMap<String, String> = LinkedMultiValueMap()
        body.add("code", authorizationCode)
        body.add("redirect_uri", redirectUri)
        return body
    }
}