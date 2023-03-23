rootProject.name = "spring-boot-kts"

include("web")
include("reactive-web")

include("adepter:r2dbc")
include("adepter:jpa")

include("domain")

include("test-support:testcontainers-core")
include("test-support:mysql")
include("test-support:postgresql")
