package com.yellow.jean.entity

import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Review
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 수선 관련 리뷰 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,
    val description: String? = null,
) : BaseTime()