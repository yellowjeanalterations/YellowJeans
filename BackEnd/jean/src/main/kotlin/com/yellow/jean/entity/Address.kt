package com.yellow.jean.entity

import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Address
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 주소 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    val postcode: String,
    @Column(name= "main_address")
    val mainAddress: String,
    @Column(name= "etc_address")
    val etcAddress: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    var user: User? = null,
) : BaseTime()