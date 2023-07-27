package com.yellow.jean.entity

import com.yellow.jean.domain.Authority
import com.yellow.jean.domain.UserDto
import jakarta.persistence.*

@Entity
data class User(  @Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long? = null,
                  @Column(nullable = false)
                  val name: String,
                  @Column(nullable = false, unique = true)
                  val email: String,
                  @Column(nullable = false)
                  val password: String,
                  @Column(nullable = false)
                  @Enumerated(EnumType.STRING)
                  val authority: Authority = Authority.USER
) : BaseTime(){
    fun toDomain(): UserDto{
        return UserDto(name, email, password, authority)
    }
}