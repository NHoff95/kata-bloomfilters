package de.nhoff95.kata.bloomfilters.mock

import de.nhoff95.kata.bloomfilters.hash.Hash

class HashToOne : Hash {
    override fun hash(toHash: ByteArray): Int = 1
}

class HashToTen : Hash {
    override fun hash(toHash: ByteArray): Int = 10
}

class HashToMinusFive : Hash {
    override fun hash(toHash: ByteArray): Int = -5
}

class HashSpecificOneOthersZero(private val specific: List<ByteArray>) : Hash {
    override fun hash(toHash: ByteArray) = when {
        specific.filter { it.contentEquals(toHash) }.any() -> 1
        else -> 0
    }

}
