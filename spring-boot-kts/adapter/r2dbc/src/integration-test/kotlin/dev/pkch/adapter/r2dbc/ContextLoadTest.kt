package dev.pkch.adapter.r2dbc

import dev.pkch.support.R2dbcPostgresqlContainerLoad
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@R2dbcPostgresqlContainerLoad
class ContextLoadTest {

    @Test
    fun contextLoad() {
    }
}