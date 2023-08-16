package com.yellow.jean.entity

import com.yellow.jean.domain.enums.Process
import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Order
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 수선 주문 관련 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity(name =  "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Enumerated(EnumType.STRING)
    var process: Process,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    var user: User? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    var store: Store? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cate_id")
    var category: Category? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    var address: Address? = null,
) : BaseTime()