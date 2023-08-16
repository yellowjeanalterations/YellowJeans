package com.yellow.jean.domain.enums

import com.yellow.jean.domain.BaseResponseBody

/**
 * packageName    : com.yellow.jean.domain.enums
 * fileName       : Authority
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 비즈니스 에러 주체 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
enum class ErrorCode(
    private val code: Int,
    val message: String
){
    DEFAULT_ERROR(0, "알 수 없는 오류입니다."),
    INVALID_PASSWORD(1, "비밀번호가 일치하지 않습니다."),
    NOT_NULL(2,"NULL 값이 들어올 수 없습니다."),
    EXT_ID_NOT_FOUND(3,"계정을 찾을 수 없습니다."),
    ;

    fun getResponseBody(): BaseResponseBody {
        return BaseResponseBody(code, message)
    }
}