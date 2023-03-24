package dev.pkch.adapter.r2dbc

import com.querydsl.sql.PostgreSQLTemplates
import com.querydsl.sql.SQLTemplates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class R2dbcConfig {

    @Bean
    fun sqlTemplates(): SQLTemplates {
        return PostgreSQLTemplates()
    }
}