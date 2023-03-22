package dev.pkch.support

import org.springframework.context.annotation.Configuration

private const val SERVICE_NAME = "postgresql"

@Configuration
class PostgresqlContainerConfiguration: DockerComposeContainerConfiguration(SERVICE_NAME)