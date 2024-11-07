package com.yooshyasha.authhwid.entity

import jakarta.persistence.*

@Entity
@Table(name = "keys")
data class Key (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var key: String? = null,
    var HWID: String? = null,
)