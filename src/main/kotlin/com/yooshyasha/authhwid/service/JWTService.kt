package com.yooshyasha.authhwid.service

import io.jsonwebtoken.Jwts
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    private val logger = LogFactory.getLog(JwtService::class.java)

    val secretKey = "superSecretKey"

    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            logger.error("Token validation error: ${e.message}")
            false
        }
    }
}