package de.nhoff95.kata.bloomfilters.utils

import org.junit.Test
import kotlin.test.assertEquals

class ModuloTest {

    @Test
    fun moduloIsCorrectForSmallNumber() {
        val result = modulo(10, 5)

        assertEquals(0, result)
    }

    @Test
    fun moduloAcceptsRangeNearMaxInteger() {
        val original = 5000
        val range = Int.MAX_VALUE

        val result = modulo(original, range)

        assertEquals(original, result)
    }

    @Test
    fun moduloIsPositiveForNegativeInput() {
        val result = modulo(-5, 10)

        assertEquals(5, result)
    }
}
