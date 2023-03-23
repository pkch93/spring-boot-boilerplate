package dev.pkch.support

import jakarta.annotation.PreDestroy
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

@Configuration
class MysqlR2dbcContainerConfiguration {
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

    class MysqlR2dbcContainerContextInitializer: ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            val r2dbcUrl = "r2dbc:mysql://${container.host}:${container.firstMappedPort}/${container.databaseName}"
            TestPropertyValues.of("spring.r2dbc.url=$r2dbcUrl").applyTo(applicationContext)
            TestPropertyValues.of("spring.r2dbc.username=${container.username}").applyTo(applicationContext)
            TestPropertyValues.of("spring.r2dbc.hikari.password=${container.password}").applyTo(applicationContext)
        }
    }
}