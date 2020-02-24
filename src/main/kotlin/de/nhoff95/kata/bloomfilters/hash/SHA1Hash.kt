package de.nhoff95.kata.bloomfilters.hash

import java.math.BigInteger
import java.security.MessageDigest

class SHA1Hash : Hash {
    override fun hash(toHash: ByteArray): Int {
        val sha1 = MessageDigest.getInstance("SHA-1")
        val hash = sha1.digest(toHash)
        return BigInteger(1, hash).toInt()
    }
}
