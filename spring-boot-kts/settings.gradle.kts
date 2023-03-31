rootProject.name = "spring-boot-kts"

include("web")
include("reactive-web")

include("adapter:core")
include("adapter:jpa")
include("adapter:r2dbc")

include("domain")

include("test-support:testcontainers-core")
include("test-support:mysql")
include("test-support:postgresql")
