package com.yooshyasha.authhwid.repository

import com.yooshyasha.authhwid.entity.Key
import org.springframework.data.jpa.repository.JpaRepository

interface KeysRepository : JpaRepository<Key, Long> {
    fun findByHWID(hwid: String): Key?

    fun findByKey(key: String): Key?
}