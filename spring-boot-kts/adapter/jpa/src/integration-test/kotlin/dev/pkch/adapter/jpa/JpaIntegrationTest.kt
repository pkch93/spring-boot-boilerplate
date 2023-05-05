package dev.pkch.adapter.jpa

import dev.pkch.support.IntegrationTest
import dev.pkch.support.jdbc.mysql.JdbcMysqlContainerLoad

@JdbcMysqlContainerLoad
abstract class JpaIntegrationTest: IntegrationTest()
