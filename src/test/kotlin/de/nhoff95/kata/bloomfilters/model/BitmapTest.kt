package de.nhoff95.kata.bloomfilters.model

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BitmapTest {

    @Test
    fun isInitializedWithFalse() {
        val tested = Bitmap(3)

        assertEquals(false, tested.isSet(0))
        assertEquals(false, tested.isSet(1))
        assertEquals(false, tested.isSet(2))
    }

    @Test
    fun setsValueAtSpecifiedPosition() {
        val tested = Bitmap(5)

        tested.set(2)

        assertEquals(true, tested.isSet(2))
    }

    @Test
    fun setsValueAtBeginning() {
        val tested = Bitmap(5)

        tested.set(0)

        assertEquals(true, tested.isSet(0))
    }

    @Test
    fun setsValueAtEnd() {
        val tested = Bitmap(5)

        tested.set(4)

        assertEquals(true, tested.isSet(4))
    }

    @Test
    fun cardinalityOfInitializedBitmapIsZero() {
        val tested = Bitmap(5)

        assertEquals(0, tested.cardinality())
    }

    @Test
    fun cardinalityAfterTwoSetsIsTwo() {
        val tested = Bitmap(5)

        tested.set(1)
        tested.set(3)

        assertEquals(2, tested.cardinality())
    }

    @Test
    fun sizeIsEqualToInitializedSize() {
        val tested = Bitmap(5)

        assertEquals(5, tested.size)
    }

    @Test
    fun settingPositionLargerThanBitmapSizeIsNotAllowed() {
        val tested = Bitmap(1)

        assertFailsWith(IllegalArgumentException::class) {
            tested.set(3)
        }
    }

    @Test
    fun settingNegativePositionIsNotAllowed() {
        val tested = Bitmap(1)

        assertFailsWith(IllegalArgumentException::class) {
            tested.set(-3)
        }
    }
}
