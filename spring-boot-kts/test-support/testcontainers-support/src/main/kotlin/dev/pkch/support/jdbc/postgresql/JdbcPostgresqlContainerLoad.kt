package dev.pkch.support.jdbc.postgresql

import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [ PostgresqlContainerConfiguration.PostgresqlContainerContextInitializer::class ])
@Import(PostgresqlContainerConfiguration::class)
annotation class JdbcPostgresqlContainerLoad
