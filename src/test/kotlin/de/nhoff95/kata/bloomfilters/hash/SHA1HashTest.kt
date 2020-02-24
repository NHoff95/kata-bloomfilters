package de.nhoff95.kata.bloomfilters.hash

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.text.Charsets.UTF_8

class SHA1HashTest {

    private lateinit var tested: SHA1Hash

    @Before
    fun setUp() {
        tested = SHA1Hash()
    }

    @Test
    fun hash() {
        val toHash = "Test"

        val result = tested.hash(toHash.toByteArray(UTF_8))

        assertEquals(2142622202, result)
    }
}
