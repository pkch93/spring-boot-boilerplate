package dev.pkch.support

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import mu.KotlinLogging
import org.springframework.core.io.ClassPathResource
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import java.io.FileOutputStream
import java.net.Socket

private val log = KotlinLogging.logger(DockerComposeContainerConfiguration::class.simpleName!!)
private const val LOCALHOST = "127.0.0.1"

abstract class DockerComposeContainerConfiguration(
    val serviceName: String
) {
    private lateinit var container: DockerComposeContainer<*>
    private lateinit var dockerComposeFile: File

    @PostConstruct
    fun start() {
        if (isContainerHealthy()) {
            log.info { "$serviceName docker container is already running..." }
            return
        }

        log.info { "$serviceName docker container start" }
        dockerComposeFile = extractDockerComposeFile("$serviceName/docker-compose.yml")
        container = DockerComposeContainer(dockerComposeFile).withServices(serviceName)
        container.start()
    }

    /**
     * docker-compose로 띄우는 컨테이너들의 포트를 확인해서 체크
     */
    private fun isContainerHealthy(): Boolean {
        return try {
            Socket(LOCALHOST, 3306).close()
            true
        } catch (exception: Exception) {
            false
        }
    }

    private fun extractDockerComposeFile(path: String): File {
        val dockerComposeInputStream = ClassPathResource(path).inputStream
        val dockerComposeFile = File.createTempFile("docker-compose", ".yml")

        FileOutputStream(dockerComposeFile).use { fos ->
            var read: Int
            val bytes = ByteArray(1024)
            while (dockerComposeInputStream.read(bytes).also { read = it } != -1) {
                fos.write(bytes, 0, read)
            }
        }

        return dockerComposeFile
    }

    @PreDestroy
    fun stop() {
        container.stop()
        dockerComposeFile.delete()
    }
}