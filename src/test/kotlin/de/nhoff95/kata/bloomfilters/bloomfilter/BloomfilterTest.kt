package de.nhoff95.kata.bloomfilters.bloomfilter

import de.nhoff95.kata.bloomfilters.mock.HashToOne
import de.nhoff95.kata.bloomfilters.mock.HashToTen
import de.nhoff95.kata.bloomfilters.model.Bitmap
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BloomfilterTest {

    private lateinit var tested: Bloomfilter

    @Test
    fun canContainValueIfAllBitsSet() {
        tested = Bloomfilter(
            datasource = prepareBitmapMatchAll(),
            hashFunctions = listOf(HashToOne(), HashToTen())
        )

        val result = tested.canContainValue("Test")

        assertTrue { result }
    }

    @Test
    fun cannotContainValueIfSomeBitsSet() {
        tested = Bloomfilter(
            datasource = prepareBitmapMatchSome(),
            hashFunctions = listOf(HashToOne(), HashToTen())
        )

        val result = tested.canContainValue("Test")

        assertFalse { result }
    }

    @Test
    fun cannotContainValueIfNoBitsSet() {
        tested = Bloomfilter(
            datasource = prepareBitmapMatchNone(),
            hashFunctions = listOf(HashToOne(), HashToTen())
        )

        val result = tested.canContainValue("Test")

        assertFalse { result }
    }

    private fun prepareBitmapMatchAll(): Bitmap {
        val bitmap = Bitmap(20)
        bitmap.set(1)
        bitmap.set(10)
        return bitmap
    }

    private fun prepareBitmapMatchSome(): Bitmap {
        val bitmap = Bitmap(20)
        bitmap.set(1)
        bitmap.set(5)
        return bitmap
    }

    private fun prepareBitmapMatchNone(): Bitmap {
        val bitmap = Bitmap(20)
        bitmap.set(5)
        bitmap.set(15)
        return bitmap
    }
}
