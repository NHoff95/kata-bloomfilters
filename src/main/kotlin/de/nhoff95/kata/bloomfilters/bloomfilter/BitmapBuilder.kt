package de.nhoff95.kata.bloomfilters.bloomfilter

import de.nhoff95.kata.bloomfilters.hash.Hash
import de.nhoff95.kata.bloomfilters.model.Bitmap
import de.nhoff95.kata.bloomfilters.utils.modulo
import kotlin.text.Charsets.UTF_8

class BitmapBuilder(
    size: Int,
    private val hashFunctions: List<Hash>
) {
    private val bitmap: Bitmap = Bitmap(size)

    fun withWord(toAdd: String) = hashFunctions.map {
        it.hash(toAdd.toByteArray(UTF_8))
    }.map {
        modulo(it, bitmap.size)
    }.forEach {
        bitmap.set(it)
    }.let {
        this
    }

    fun build() = bitmap

}
