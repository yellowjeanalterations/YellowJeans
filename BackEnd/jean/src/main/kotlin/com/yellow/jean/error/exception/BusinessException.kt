package com.yellow.jean.error.exception

import com.yellow.jean.domain.enums.ErrorCode
/**
 * packageName    : com.yellow.jean.error.exception
 * fileName       : BusinessException
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 비즈니스 에러 관련 객체 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
data class BusinessException(
    val error: ErrorCode
): RuntimeException(error.message)
