package dev.pkch.support

import jakarta.annotation.PreDestroy
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@Configuration
class PostgresqlR2dbcContainerConfiguration {
    companion object {
        @JvmStatic
        private val container = PostgreSQLContainer(DockerImageName.parse("postgres:15.2"))
            .apply { start() }
    }

    @PreDestroy
    fun stop() {
        container.stop()
        container.close()
    }

    class PostgresqlR2dbcContainerContextInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            val r2dbcUrl = "r2dbc:postgres://${container.host}:${container.firstMappedPort}/${container.databaseName}"
            TestPropertyValues.of("spring.r2dbc.url=$r2dbcUrl").applyTo(applicationContext)
            TestPropertyValues.of("spring.r2dbc.username=${container.username}").applyTo(applicationContext)
            TestPropertyValues.of("spring.r2dbc.hikari.password=${container.password}").applyTo(applicationContext)
        }
    }
}