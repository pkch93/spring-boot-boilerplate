package dev.pkch.adapter.jpa

import dev.pkch.support.JdbcMysqlContainerLoad
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@JdbcMysqlContainerLoad
class ContextLoadTest {

    @Test
    fun contextLoad() {
    }
}