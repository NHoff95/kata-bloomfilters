package de.nhoff95.kata.bloomfilters.bloomfilter

import de.nhoff95.kata.bloomfilters.hash.Hash
import de.nhoff95.kata.bloomfilters.model.Bitmap
import de.nhoff95.kata.bloomfilters.utils.modulo
import kotlin.text.Charsets.UTF_8

class Bloomfilter(
    private val datasource: Bitmap,
    private val hashFunctions: List<Hash>
) {
    fun canContainValue(valueToCheck: String): Boolean = hashFunctions.map {
        it.hash(valueToCheck.toByteArray(UTF_8))
    }.map {
        modulo(it, datasource.size)
    }.map {
        datasource.isSet(it)
    }.all {
        it
    }
}
