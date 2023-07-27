package com.yellow.jean.error.exception

import com.yellow.jean.domain.BaseResponseBody


enum class ErrorCode(
    private val code: Int,
    val message: String
){
    DEFAULT_ERROR(0, "알 수 없는 오류입니다."),
    INVALID_PASSWORD(1, "비밀번호가 일치하지 않습니다."),
    NOT_NULL(2,"NULL 값이 들어올 수 없습니다."),
    EMAIL_NOT_FOUND(3,"계정을 찾을 수 없습니다."),
    ;

    fun getResponseBody(): BaseResponseBody {
        return BaseResponseBody(code, message)
    }
}