package com.yooshyasha.authhwid.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService {
    private val logger = LoggerFactory.getLogger(AuthService::class.java)

    val secretKey: String = generateSecretKey()

    private final fun generateSecretKey(): String {
        val key = UUID.randomUUID().toString()
        logger.info("Generated secret key - $key")
        return key
    }
}