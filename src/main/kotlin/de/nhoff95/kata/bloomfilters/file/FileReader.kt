package de.nhoff95.kata.bloomfilters.file

import java.io.File
import java.lang.Character.isLetter
import java.net.URI
import kotlin.text.Charsets.UTF_8

class FileReader {

    fun readWordsPerLine(absolutePath: URI): Set<String> {
        val file = File(absolutePath)
        return file.readLines(charset = UTF_8).map {
            it.trim()
        }.filter {
            it.isNotEmpty()
        }.toSet()
    }

    fun readWordsBySeparator(absolutePath: URI, separator: String): Set<String> {
        val file = File(absolutePath)
        return file.readLines(charset = UTF_8).flatMap {
            it.split(separator)
        }.map {
            it.trim()
        }.filter {
            it.isNotEmpty()
        }.map {
            it.toCharArray().filter(::isLetter).joinToString("")
        }.toSet()
    }
}
