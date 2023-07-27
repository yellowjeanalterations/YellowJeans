package com.yellow.jean.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
    private val user: UserDto
): UserDetails{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.getAuthority()
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun getUsername(): String {
        return user.email!!
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