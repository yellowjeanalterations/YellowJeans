package com.yellow.jean.repository

import com.yellow.jean.entity.User
import org.springframework.data.repository.CrudRepository
/**
 * packageName    : com.yellow.jean.repository
 * fileName       : UserRepository
 * author         : iseongmu
 * date           : 2023/08/14
 * description    : 유저 CRUD Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/08/14        iseongmu       최초 생성
 */
interface UserRepository: CrudRepository<User, Long> {
    fun findByExtId(extId: String): User?
}