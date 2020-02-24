package de.nhoff95.kata.bloomfilters.spellcheck

import de.nhoff95.kata.bloomfilters.bloomfilter.Bloomfilter

class SpellChecker {
    fun check(wordsToCheck: Set<String>, bloomfilter: Bloomfilter): Boolean = wordsToCheck.map { wordToCheck ->
        val result = bloomfilter.canContainValue(wordToCheck)
        Pair(wordToCheck, result)
    }.filter { (checkedWord, isCorrect) ->
        !isCorrect.also { if (!isCorrect) println("Found possible error: $checkedWord") }
    }.none()
}
