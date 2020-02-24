package de.nhoff95.kata.bloomfilters.bloomfilter

import de.nhoff95.kata.bloomfilters.mock.HashToMinusFive
import de.nhoff95.kata.bloomfilters.mock.HashToOne
import de.nhoff95.kata.bloomfilters.mock.HashToTen
import org.junit.Assert.assertEquals
import org.junit.Test

class BitmapBuilderTest {

    private lateinit var tested: BitmapBuilder

    @Test
    fun setsPositionAccordingToHash() {
        tested = BitmapBuilder(size = 3, hashFunctions = listOf(HashToOne()))

        val result = tested.withWord("Test").build()

        assertEquals(true, result.isSet(1))
        assertEquals(1, result.cardinality())
    }

    @Test
    fun setsModuloPositionIfHashLargerThanBitmapSize() {
        tested = BitmapBuilder(size = 5, hashFunctions = listOf(HashToTen()))

        val result = tested.withWord("Test").build()

        assertEquals(true, result.isSet(0))
        assertEquals(1, result.cardinality())
    }

    @Test
    fun setsPositiveModuloPositionIfHashNegative() {
        tested = BitmapBuilder(size = 10, hashFunctions = listOf(HashToMinusFive()))

        val result = tested.withWord("Test").build()

        assertEquals(true, result.isSet(5))
        assertEquals(1, result.cardinality())
    }

    @Test
    fun positionStaysSetOnCollision() {
        tested = BitmapBuilder(size = 5, hashFunctions = listOf(HashToOne()))

        val result = tested
            .withWord("Test")
            .withWord("AnotherTest")
            .build()

        assertEquals(true, result.isSet(1))
        assertEquals(1, result.cardinality())
    }

    @Test
    fun setsPositionFromTwoHashFunctions() {
        tested = BitmapBuilder(size = 20, hashFunctions = listOf(HashToOne(), HashToTen()))

        val result = tested.withWord("Test").build()

        assertEquals(true, result.isSet(1))
        assertEquals(true, result.isSet(10))
        assertEquals(2, result.cardinality())
    }
}
