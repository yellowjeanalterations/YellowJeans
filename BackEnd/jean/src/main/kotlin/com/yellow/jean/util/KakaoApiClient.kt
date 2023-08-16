package com.yellow.jean.util

import com.yellow.jean.domain.KaKaoLoginResponse
import com.yellow.jean.domain.OAuthLoginParam
import com.yellow.jean.domain.enums.AuthProvider
import com.yellow.jean.domain.enums.ErrorCode
import com.yellow.jean.error.exception.BusinessException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

/**
 * TEST
 * https://kauth.kakao.com/oauth/authorize?client_id=a43146847a448fdf87f3f901b2205c46&redirect_uri=http://localhost:8082/kakao/callback&response_type=code
 * http://localhost:8082/kakao/callback?code=*/
/**
 * packageName    : com.yellow.jean.util
 * fileName       : KakaoApiClient
 * author         : iseongmu
 * date           : 2023/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/15        iseongmu       최초 생성
 */
@Component
class KakaoApiClient(
    val webClientBuilder: WebClient.Builder,
) : OAuthApiClient {
    private val log: Logger = LoggerFactory.getLogger(KakaoApiClient::class.java)
    @Value("\${oauth.kakao.client-id}")
    lateinit var clientId: String
    @Value("\${oauth.kakao.url.api}")
    lateinit var apiUrl: String
    @Value("\${oauth.kakao.url.auth}")
    lateinit var authUrl: String

    override fun oAuthProvider(): AuthProvider? {
        return AuthProvider.KAKAO
    }

    override fun requestAccessToken(params: OAuthLoginParam?): KaKaoLoginResponse? {
        val webClient = webClientBuilder
                        .baseUrl(apiUrl)
                        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .build().mutate().build()
        val body = params!!.makeBody()
        body.add("grant_type", "authorization_code")
        body.add("client_id", clientId)
        return webClient.post()
            .uri("$authUrl/oauth/token")
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromFormData(body))
            .retrieve()
            .onStatus(HttpStatusCode::is5xxServerError){ response -> response.body { inputMessage, context -> log.error(inputMessage.toString()) }
                throw BusinessException(ErrorCode.DEFAULT_ERROR)}
            .onStatus(HttpStatusCode::is4xxClientError){ response -> response.body { inputMessage, context -> log.error(inputMessage.toString()) }
                throw BusinessException(ErrorCode.DEFAULT_ERROR)}
            .bodyToMono(KaKaoLoginResponse::class.java)
            .block()
    }
}