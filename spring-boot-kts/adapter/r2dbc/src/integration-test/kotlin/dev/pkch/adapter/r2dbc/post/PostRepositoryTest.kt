package dev.pkch.adapter.r2dbc.post

import dev.pkch.support.R2dbcPostgresqlContainerLoad
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@R2dbcPostgresqlContainerLoad
class PostRepositoryTest {
    @Autowired
    private lateinit var sut: PostRepository

    @Test
    fun save() {
        val post = sut.save(Post("title", "content")).block()!!

        assertThat(post.id).isEqualTo(1L)
        assertThat(post.title).isEqualTo("title")
        assertThat(post.content).isEqualTo("content")
    }
}