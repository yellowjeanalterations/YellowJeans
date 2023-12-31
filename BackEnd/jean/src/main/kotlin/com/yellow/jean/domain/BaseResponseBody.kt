package com.yellow.jean.domain
/**
 * packageName    : com.yellow.jean.domain
 * fileName       : BaseResponseBody
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : Return Object 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
data class BaseResponseBody(
    val code: Int,
    val message: String,
)
