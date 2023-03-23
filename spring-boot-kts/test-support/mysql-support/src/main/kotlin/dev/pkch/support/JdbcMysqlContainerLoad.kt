package dev.pkch.support

import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(initializers = [ MysqlContainerConfiguration.MysqlContainerContextInitializer::class ])
@Import(MysqlContainerConfiguration::class)
annotation class JdbcMysqlContainerLoad
