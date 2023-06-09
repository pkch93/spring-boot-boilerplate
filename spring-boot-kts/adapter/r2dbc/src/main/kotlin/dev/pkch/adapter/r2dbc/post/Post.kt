package dev.pkch.adapter.r2dbc.post

import org.springframework.data.annotation.Id

data class Post(
    val title: String,
    val content: String,
    @Id
    val id: Long? = null
)
