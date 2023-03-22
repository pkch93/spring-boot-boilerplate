rootProject.name = "spring-boot-kts"

include("web")
include("reactive-web")

include("adepter:r2dbc")
include("adepter:jpa")

include("domain")

include("test-support:support-core")
include("test-support:mysql-support")
include("test-support:postgresql-support")
