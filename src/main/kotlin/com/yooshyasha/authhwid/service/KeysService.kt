package com.yooshyasha.authhwid.service

import com.yooshyasha.authhwid.entity.Key
import com.yooshyasha.authhwid.repository.KeysRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class KeysService(
    private val keysRepository: KeysRepository
) {
    fun checkKey(key: Key, hwid: String?) : Boolean {
        return key.HWID == hwid
    }

    fun addKey(key: Key) {
        keysRepository.save(key)
    }

    fun updateKey(key: Key) {
        keysRepository.save(key)
    }

    fun removeKey(key: Key) {
        keysRepository.delete(key)
    }

    fun getKey(key: String) : Key? {
        return keysRepository.findByKey(key)
    }

    fun generateKey() : Key {
        return Key(key = UUID.randomUUID().toString())
    }
}