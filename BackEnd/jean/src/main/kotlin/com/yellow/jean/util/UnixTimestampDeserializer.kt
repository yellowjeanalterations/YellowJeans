package com.yellow.jean.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.time.LocalDateTime

/**
 * packageName    : com.yellow.jean.util
 * fileName       : UnixTimestampDeserializer
 * author         : iseongmu
 * date           : 2023/08/15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/15        iseongmu       최초 생성
 */
class UnixTimestampDeserializer: JsonDeserializer<LocalDateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        val unixTimestamp: String = p?.text?.trim() ?: "0"
        //return Instant.ofEpochSecond(unixTimestamp.toLong()).atZone(ZoneId.systemDefault()).toLocalDateTime()
        return LocalDateTime.now().plusSeconds(unixTimestamp.toLong())
    }
}