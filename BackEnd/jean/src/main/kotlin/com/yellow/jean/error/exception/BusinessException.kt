package com.yellow.jean.error.exception


data class BusinessException(
    val error: ErrorCode
): RuntimeException(error.message)
