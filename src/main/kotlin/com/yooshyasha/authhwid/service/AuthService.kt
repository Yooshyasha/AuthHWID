package com.yooshyasha.authhwid.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService {
    val secretKey: String = generateSecretKey()

    private final fun generateSecretKey(): String {
        return UUID.randomUUID().toString()
    }
}