package com.yooshyasha.authhwid.controller

import com.yooshyasha.authhwid.dto.api.CheckKeyDTO
import com.yooshyasha.authhwid.entity.Key
import com.yooshyasha.authhwid.service.KeysService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/keys")
class KeysController(
    private val keysService: KeysService
) {
    /*
    Errors:
    400 - Key not exist

    Returned:
    true - if key.hwid == hwid
    false - else

    Function deleted key, if key obsolete
     */
    @GetMapping("/checkKey")
    fun checkKey(@RequestBody request: CheckKeyDTO) : ResponseEntity<Boolean> {
        val key = request.key.key?.let { keysService.getKey(it) }

        if (key == null) {
            return ResponseEntity.badRequest().body(false)
        }

        val result = keysService.checkKey(key, request.hwid)

//        if (!result) { TODO
//            key?.let { keysService.removeKey(it) }
//        }

        return ResponseEntity.ok(result)
    }

    /*
    Errors:
    400 - Key not exist or already bind

    Function binded key to HWID
     */
    @PostMapping("/bindHWID")
    fun bindHWID(@RequestBody request: CheckKeyDTO) : ResponseEntity<Map<String, Boolean>> {
        val key = request.key.key?.let { keysService.getKey(it) }

        if (key == null) {
            return ResponseEntity.badRequest().body(mapOf())
        }

        if (key.HWID != null) {
            return ResponseEntity.badRequest().body(mapOf())
        }

        key.HWID = request.hwid
        keysService.updateKey(key)

        return ResponseEntity.ok(mapOf("success" to true))
    }

    /*
    Errors:
    401 - UnAuth

    Function create empty key
     */
    @PostMapping("/createKey")
    fun createKey(): ResponseEntity<String> {
        val key = keysService.generateKey()
        keysService.addKey(key)

        return ResponseEntity.ok(key.key)
    }

}