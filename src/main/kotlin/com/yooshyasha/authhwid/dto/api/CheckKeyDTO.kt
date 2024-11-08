package com.yooshyasha.authhwid.dto.api

import com.yooshyasha.authhwid.entity.Key

data class CheckKeyDTO(
    val key: Key,
    val hwid: String?,
)
