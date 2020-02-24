package de.nhoff95.kata.bloomfilters

import de.nhoff95.kata.bloomfilters.bloomfilter.BitmapBuilder
import de.nhoff95.kata.bloomfilters.bloomfilter.Bloomfilter
import de.nhoff95.kata.bloomfilters.file.FileReader
import de.nhoff95.kata.bloomfilters.hash.Hash
import de.nhoff95.kata.bloomfilters.hash.MD5Hash
import de.nhoff95.kata.bloomfilters.hash.SHA1Hash
import de.nhoff95.kata.bloomfilters.model.Bitmap
import de.nhoff95.kata.bloomfilters.spellcheck.SpellChecker
import java.io.File
import java.net.URI

val HASH_FUNCTIONS: List<Hash> = listOf(MD5Hash(), SHA1Hash())

fun main(args: Array<String>) {
    if (args.size != 3)
        return printHelp("Unexpected number of parameters.")
    val bitmapSize = extractBitmapSize(args[0])
        ?: return printHelp("Invalid bitmap size, not a valid Int.")
    val pathToDictionary = extractValidPath(args[1])
        ?: return printHelp("Invalid dictionary path, path is not absolute or file does not exist.")
    val fileToCheck = extractValidPath(args[2])
        ?: return printHelp("Invalid path for file to be checked, path is not absolute or file does not exist.")

    val fileReader = FileReader()
    val spellChecker = SpellChecker()
    val dictionary = fileReader.readWordsPerLine(pathToDictionary)
    val wordsToCheck = fileReader.readWordsBySeparator(fileToCheck, " ")
    val datasource = prepareDatasource(bitmapSize, HASH_FUNCTIONS, dictionary)
    val bloomfilter = Bloomfilter(datasource, HASH_FUNCTIONS)

    when (spellChecker.check(wordsToCheck, bloomfilter)) {
        true -> println("All words in file seem to be correct.")
        false -> System.err.println("Found words in file which might be wrong.")
    }
}

private fun prepareDatasource(bitmapSize: Int, hashFunctions: List<Hash>, dictionary: Set<String>): Bitmap {
    val datasource = BitmapBuilder(bitmapSize, hashFunctions)
    for (word in dictionary) datasource.withWord(word)
    return datasource.build()
}

private fun extractBitmapSize(size: String): Int? = runCatching { size.toInt() }.getOrNull()

private fun printHelp(additionalMessage: String? = null) {
    additionalMessage?.let { println(it) }
    println("Use with following parameters: <bitmap size> <absolutepath/to/dictionary> <absolutepath/to/texttocheck>")
    println("Use \"exit\" to leave the program.")
}

private fun extractValidPath(path: String): URI? {
    val file = File(path)
    return if (file.isAbsolute && file.exists()) file.toURI() else null
}
