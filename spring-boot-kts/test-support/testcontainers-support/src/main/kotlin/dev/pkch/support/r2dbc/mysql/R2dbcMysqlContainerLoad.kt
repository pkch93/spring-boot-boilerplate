package dev.pkch.support.r2dbc.mysql

import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [ MysqlR2dbcContainerConfiguration.MysqlR2dbcContainerContextInitializer::class ])
@Import(MysqlR2dbcContainerConfiguration::class)
annotation class R2dbcMysqlContainerLoad
