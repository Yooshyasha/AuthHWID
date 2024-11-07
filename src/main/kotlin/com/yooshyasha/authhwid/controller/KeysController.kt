package com.yooshyasha.authhwid.controller

import com.yooshyasha.authhwid.service.KeysService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/keys")
class KeysController(
    private val keysService: KeysService
) {
    @PostMapping("/createKey")
    fun createKey(): ResponseEntity<String> {
        val key = keysService.generateKey()
        keysService.addKey(key)

        return ResponseEntity.ok(key.HWID)
    }
}