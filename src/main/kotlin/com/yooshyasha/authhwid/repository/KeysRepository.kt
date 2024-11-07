package com.yooshyasha.authhwid.repository

import com.yooshyasha.authhwid.entity.Key
import org.springframework.data.jpa.repository.JpaRepository

interface KeysRepository : JpaRepository<Key, Long> {
    fun getByHWID(hwid: String): Key?

    fun getByKey(key: String): Key?
}