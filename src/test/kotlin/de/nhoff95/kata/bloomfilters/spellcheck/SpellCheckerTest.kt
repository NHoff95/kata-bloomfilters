package de.nhoff95.kata.bloomfilters.spellcheck

import de.nhoff95.kata.bloomfilters.bloomfilter.Bloomfilter
import de.nhoff95.kata.bloomfilters.mock.HashSpecificOneOthersZero
import de.nhoff95.kata.bloomfilters.model.Bitmap
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

import kotlin.text.Charsets.UTF_8

class SpellCheckerTest {

    private lateinit var tested: SpellChecker

    @Before
    fun setUp() {
        tested = SpellChecker()
    }

    @Test
    fun returnsTrueIfAllWordsAreCorrect() {
        val wordsToCheck = setOf("some", "word")
        val wordsToAssumeCorrect = listOf(
            "some".toByteArray(UTF_8),
            "word".toByteArray(UTF_8)
        )
        val bloomfilter = Bloomfilter(
            datasource = prepareBitmap(),
            hashFunctions = listOf(HashSpecificOneOthersZero(specific = wordsToAssumeCorrect))
        )

        val result = tested.check(wordsToCheck, bloomfilter)

        assertTrue { result }
    }

    @Test
    fun returnsFalseIfNotAllWordsAreCorrect() {
        val wordsToCheck = setOf("some", "word")
        val wordsToAssumeCorrect = listOf(
            "some".toByteArray(UTF_8)
        )
        val bloomfilter = Bloomfilter(
            datasource = prepareBitmap(),
            hashFunctions = listOf(HashSpecificOneOthersZero(specific = wordsToAssumeCorrect))
        )

        val result = tested.check(wordsToCheck, bloomfilter)

        assertFalse { result }
    }

    @Test
    fun returnsFalseIfNoWordsAreCorrect() {
        val wordsToCheck = setOf("some", "word")
        val bloomfilter = Bloomfilter(
            datasource = prepareBitmap(),
            hashFunctions = listOf(HashSpecificOneOthersZero(specific = emptyList()))
        )

        val result = tested.check(wordsToCheck, bloomfilter)

        assertFalse { result }
    }

    private fun prepareBitmap(): Bitmap {
        val bitmap = Bitmap(2)
        bitmap.set(1)
        return bitmap
    }
}
