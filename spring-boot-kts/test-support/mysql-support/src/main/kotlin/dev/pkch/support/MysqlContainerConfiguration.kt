package dev.pkch.support

import org.springframework.context.annotation.Configuration

private const val SERVICE_NAME = "mysql"

@Configuration
class MysqlContainerConfiguration: DockerComposeContainerConfiguration(SERVICE_NAME)