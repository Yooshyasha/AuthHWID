package com.yooshyasha.authhwid.controller

import com.yooshyasha.authhwid.dto.api.AuthJWTDTO
import com.yooshyasha.authhwid.service.AuthService
import com.yooshyasha.authhwid.service.JwtService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
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
        if (request.secretKey != authService.secretKey) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        val token = Jwts.builder()
            .setSubject("admin")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 3600000))
            .signWith(SignatureAlgorithm.HS256, jwtService.secretKey)
            .compact()

        return ResponseEntity.ok(mapOf("token" to token))
    }

}