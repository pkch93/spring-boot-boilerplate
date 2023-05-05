rootProject.name = "spring-boot-kts"

include("web")
include("reactive-web")

include("adapter:core")
include("adapter:jpa")
include("adapter:r2dbc")

include("domain")

include("test-support:test-core")
include("test-support:testcontainers-support")
