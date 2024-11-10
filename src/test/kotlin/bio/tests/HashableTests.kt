package bio.tests

import bio.prelude.Hashable
import bio.prelude.NoopHashAlgorithm
import bio.prelude.OverrideHashingAlgorithm
import bio.prelude.hash
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HashableTests {
    @Test
    fun `should override hash`() {
        // given
        val expected = "override"

        class ExampleHashable(override val value: String) : Hashable

        // when
        val actual = ExampleHashable("test1234").hash<OverrideHashingAlgorithm>()

        // then
        assertEquals(expected, actual)
    }

    @Test
    fun `should noop hash`() {
        // given
        class ExampleHashable(override val value: String) : Hashable

        val expected = "test"

        // when
        val actual = ExampleHashable(expected).hash<NoopHashAlgorithm>()

        // then
        assertEquals(expected, actual)
    }
}
