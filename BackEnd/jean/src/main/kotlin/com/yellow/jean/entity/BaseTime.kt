package com.yellow.jean.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : BaseTime
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 공통 Entity 추상 클래스 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime? = null

    @UpdateTimestamp
    @Column(nullable = false)
    val updatedAt: LocalDateTime? = null
}