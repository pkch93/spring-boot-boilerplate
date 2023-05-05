package dev.pkch.adapter.r2dbc

import dev.pkch.support.IntegrationTest
import dev.pkch.support.r2dbc.postgresql.R2dbcPostgresqlContainerLoad

@R2dbcPostgresqlContainerLoad
class R2dbcIntegrationTest: IntegrationTest() {
}