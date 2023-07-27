package com.yellow.jean.domain

import com.yellow.jean.entity.User
import com.yellow.jean.error.exception.BusinessException
import com.yellow.jean.error.exception.ErrorCode
import org.springframework.security.core.GrantedAuthority

data class UserDto(
    var name: String?,
    var email: String?,
    var password: String?,
    var authority: Authority?
) {
    fun toEntity(): User{
        if(name == null || email == null || password == null) throw BusinessException(ErrorCode.NOT_NULL)
        return User(name= name!!, email= email!!, password =  password!!)
    }
    fun getAuthority(): MutableCollection<out GrantedAuthority>{
        return mutableListOf(GrantedAuthority{authority?.name})
    }
}