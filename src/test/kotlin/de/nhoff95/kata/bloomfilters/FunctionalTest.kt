package de.nhoff95.kata.bloomfilters

import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.lang.System.setErr
import java.lang.System.setOut
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.text.Charsets.UTF_8

class FunctionalTest {

    @Test
    fun canReadCorrectText() {
        val outputBuffer = ByteArrayOutputStream()
        setOut(PrintStream(outputBuffer))
        val params: Array<String> = prepareParameters(textToAnalyze = CORRECT_TEXT)

        main(params)
        val outputResult = outputBuffer.toString(UTF_8)

        assertTrue { outputResult.contains("All words in file seem to be correct.") }
    }

    @Test
    fun printsIncorrectWordsInText() {
        val outputBuffer = ByteArrayOutputStream()
        setOut(PrintStream(outputBuffer))
        setErr(PrintStream(outputBuffer))
        val params: Array<String> = prepareParameters(textToAnalyze = INCORRECT_TEXT)

        main(params)
        val outputResult = outputBuffer.toString(UTF_8)

        assertTrue { outputResult.contains("Found words in file which might be wrong.") }
        assertTrue { outputResult.contains("Found possible error: ths") }
        assertTrue { outputResult.contains("Found possible error: sommme") }
        assertTrue { outputResult.contains("Found possible error: invalidtext") }
        assertFalse { outputResult.contains("is") }
    }

    private fun prepareParameters(textToAnalyze: String): Array<String> = arrayOf(
        "5000000",
        File("src/test/resources/wordlist.txt").absolutePath,
        File("src/test/resources/$textToAnalyze").absolutePath
    )

    companion object {
        private const val CORRECT_TEXT = "correct_text"
        private const val INCORRECT_TEXT = "incorrect_text"
    }
}
