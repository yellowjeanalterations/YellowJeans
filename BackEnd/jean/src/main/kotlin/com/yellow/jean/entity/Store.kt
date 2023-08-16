package com.yellow.jean.entity

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Store
 * author         : LSM
 * date           : 2023/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        LSM       최초 생성
 */
@Entity
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    val name: String,
    @Column(name = "pickup_time",nullable = false)
    val pickupTime: LocalDateTime,
    @Column(nullable = false)
    val introduction: String,
    @Column(nullable = false)
    val phone: String,
    @Column(nullable = false)
    val latitude: Double,
    @Column(nullable = false)
    val longitude: Double,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    var address: Address? = null,
) : BaseTime()