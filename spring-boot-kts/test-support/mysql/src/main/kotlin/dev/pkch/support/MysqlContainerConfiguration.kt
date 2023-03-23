package dev.pkch.support

import jakarta.annotation.PreDestroy
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@Configuration
class MysqlContainerConfiguration {
    companion object {
        @JvmStatic
        private val container = MySQLContainer(DockerImageName.parse("mysql:8.0.32"))
            .apply { start() }
    }

    @PreDestroy
    fun stop() {
        container.stop()
        container.close()
    }

    class MysqlContainerContextInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            TestPropertyValues.of("spring.datasource.hikari.jdbc-url=${container.jdbcUrl}").applyTo(applicationContext)
            TestPropertyValues.of("spring.datasource.hikari.username=${container.username}").applyTo(applicationContext)
            TestPropertyValues.of("spring.datasource.hikari.password=${container.password}").applyTo(applicationContext)
        }
    }
}