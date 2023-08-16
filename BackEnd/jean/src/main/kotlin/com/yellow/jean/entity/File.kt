package com.yellow.jean.entity

import jakarta.persistence.*
/**
 * packageName    : com.yellow.jean.entity
 * fileName       : File
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 파일 첨부 공통 Entity 명시
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "full_path", nullable = false)
    open val fullPath: String = "",
    @Column(name = "folder_path", nullable = false)
    open val folderPath: String = "",
    @Column(name = "file_name", nullable = false)
    open val fileName: String = ""
) : BaseTime()