package dev.pkch.adapter.r2dbc.post

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository

interface PostRepository: QuerydslR2dbcRepository<Post, Long>  {
}