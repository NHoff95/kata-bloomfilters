package de.nhoff95.kata.bloomfilters.hash

interface Hash {

    /**
     * Hashes the given value and returns an integer representation of the hash
     */
    fun hash(toHash: ByteArray): Int

}
