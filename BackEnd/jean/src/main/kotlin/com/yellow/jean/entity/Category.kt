package com.yellow.jean.entity

import jakarta.persistence.*

/**
 * packageName    : com.yellow.jean.entity
 * fileName       : Category
 * author         : iseongmu
 * date           : 2023/08/14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    var childs: List<Category>? = null,
) : BaseTime()