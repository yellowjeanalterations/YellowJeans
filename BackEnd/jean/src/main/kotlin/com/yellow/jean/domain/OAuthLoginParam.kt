package com.yellow.jean.domain

import com.yellow.jean.domain.enums.AuthProvider
import org.springframework.util.MultiValueMap




/**
 * packageName    : com.yellow.jean.domain
 * fileName       : OAuthLoginParam
 * author         : iseongmu
 * date           : 2023/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
interface OAuthLoginParam {
    fun authProvider(): AuthProvider
    fun makeBody(): MultiValueMap<String, String>
}