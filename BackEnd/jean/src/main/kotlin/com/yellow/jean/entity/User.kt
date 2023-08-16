package com.yellow.jean.entity

import com.yellow.jean.domain.enums.Authority
import com.yellow.jean.domain.UserDto
import com.yellow.jean.domain.enums.AuthProvider
import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : User
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 유저 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    val name: String,
    var height: Double? = null,
    var weight: Double? = null,
    var bust: Double? = null,
    var hip: Double? = null,
    var thigh: Double? = null,
    var waist: Double? = null,
    @Column(name = "ext_id", nullable = false, unique = true)
    val extId: String,
    @Column(name = "ext_token")
    var extToken: String,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val authProvider: AuthProvider,
    @Enumerated(EnumType.STRING)
    val authority: Authority = Authority.USER,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    var addressList: List<Address> = listOf()
) : BaseTime() {
    fun toDomain(): UserDto {
        return UserDto(id, name, height, weight, bust, hip, thigh, waist, extId, extToken, authProvider, authority)
    }
    fun bodySizeSetting(userDto: UserDto): Unit{
       height=userDto.height
       weight=userDto.weight
       bust=userDto.bust
       hip=userDto.hip
       thigh=userDto.thigh
       waist=userDto.waist
    }
}