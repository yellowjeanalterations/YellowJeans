package com.yellow.jean.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
/**
 * packageName    : com.yellow.jean.domain
 * fileName       : UserDetailsImpl
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 시큐리티 인증 객체 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
data class UserDetailsImpl(
    val user: UserDto
): UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.getAuthority()
    }

    override fun getPassword(): String {
        return ""
    }


    override fun getUsername(): String {
        return ""
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}