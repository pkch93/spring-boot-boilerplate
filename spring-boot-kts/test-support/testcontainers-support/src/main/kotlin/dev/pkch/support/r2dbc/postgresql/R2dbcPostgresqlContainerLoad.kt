package dev.pkch.support.r2dbc.postgresql

import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [ PostgresqlR2dbcContainerConfiguration.PostgresqlR2dbcContainerContextInitializer::class ])
@Import(PostgresqlR2dbcContainerConfiguration::class)
annotation class R2dbcPostgresqlContainerLoad
