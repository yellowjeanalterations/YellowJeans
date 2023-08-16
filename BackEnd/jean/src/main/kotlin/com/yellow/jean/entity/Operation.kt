package com.yellow.jean.entity

import jakarta.persistence.*
import java.time.DayOfWeek
import java.time.LocalDateTime
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Operation
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 수선집 운영 시간 관련 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class Operation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    var store: Store? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    val dayOfWeek: DayOfWeek,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
): BaseTime()