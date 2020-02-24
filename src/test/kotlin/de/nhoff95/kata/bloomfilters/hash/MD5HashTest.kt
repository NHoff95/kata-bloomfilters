package de.nhoff95.kata.bloomfilters.hash

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.text.Charsets.UTF_8

class MD5HashTest {

    private lateinit var tested: MD5Hash

    @Before
    fun setUp() {
        tested = MD5Hash()
    }

    @Test
    fun hash() {
        val toHash = "Test"

        val result = tested.hash(toHash.toByteArray(UTF_8))

        assertEquals(-916823717, result)
    }
}
