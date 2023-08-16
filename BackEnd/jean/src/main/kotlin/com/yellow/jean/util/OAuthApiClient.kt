package com.yellow.jean.util

import com.yellow.jean.domain.OAuthLoginParam
import com.yellow.jean.domain.enums.AuthProvider

/**
 * packageName    : com.yellow.jean.util
 * fileName       : OAuthApiClient
 * author         : iseongmu
 * date           : 2023/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/15        iseongmu       최초 생성
 */
interface OAuthApiClient {
    fun oAuthProvider(): AuthProvider?
    fun requestAccessToken(params: OAuthLoginParam?): Any?
}