package com.yellow.jean.entity

import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : ReviewFile
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 수선 관련 리뷰 첨부 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
data class ReviewFile(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreign_id")
    var review: Review? = null,
    override val fullPath: String,
    override val folderPath: String,
    override val fileName: String,
): File(null, fullPath, folderPath, fileName)