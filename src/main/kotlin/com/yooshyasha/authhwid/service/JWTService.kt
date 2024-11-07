package com.yooshyasha.authhwid.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {

    val secretKey: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun isTokenValid(token: String) : Boolean {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(secretKey)  // Устанавливаем секретный ключ
                .build()
                .parseClaimsJws(token)
                .body

            !claims.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}