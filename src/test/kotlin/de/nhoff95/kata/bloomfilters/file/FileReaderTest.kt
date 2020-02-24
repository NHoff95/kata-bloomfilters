package de.nhoff95.kata.bloomfilters.file

import org.junit.Before
import org.junit.Test

import java.io.File
import kotlin.test.assertEquals

class FileReaderTest {

    private lateinit var tested: FileReader

    @Before
    fun setUp() {
        tested = FileReader()
    }

    @Test
    fun readsCorrectNumberOfLinesInWordList() {
        val pathToWordList = File("src/test/resources/wordlist.txt").absoluteFile.toURI()

        val result = tested.readWordsPerLine(pathToWordList)

        assertEquals(338882, result.size)
    }

    @Test
    fun readsBeginningAndEndOfWordListCorrectly() {
        val pathToWordList = File("src/test/resources/wordlist.txt").absoluteFile.toURI()

        val result = tested.readWordsPerLine(pathToWordList)

        assertEquals("A", result.first())
        assertEquals("�v�nements", result.last())
    }

    @Test
    fun readsTextCorrectlyWithoutSymbols() {
        val expected = setOf("thiiis", "is", "an", "example", "text", "with", "two", "lines", "of", "in", "totttal")
        val pathToText = File("src/test/resources/text").absoluteFile.toURI()

        val result = tested.readWordsBySeparator(pathToText, " ")

        assertEquals(expected, result)
    }
}
