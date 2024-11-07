package com.yooshyasha.authhwid.controller

import com.yooshyasha.authhwid.dto.api.AuthJWTDTO
import com.yooshyasha.authhwid.service.AuthService
import com.yooshyasha.authhwid.service.JwtService
import io.jsonwebtoken.Jwts
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtService: JwtService,
) {

    @GetMapping("/jwt")
    fun authJWT(@RequestBody request: AuthJWTDTO) : ResponseEntity<Map<String, String>> {
//        if (request.secretKey != authService.secretKey) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
//        }

        val secret = jwtService.secretKey

        val token = Jwts.builder()
            .setIssuedAt(Date()) // Устанавливаем время создания токена
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Устанавливаем время истечения токена
            .signWith(secret)
            .compact()

        return ResponseEntity.ok(mapOf("token" to token))
    }

}