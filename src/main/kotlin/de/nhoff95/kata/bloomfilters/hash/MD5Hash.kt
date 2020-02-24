package de.nhoff95.kata.bloomfilters.hash

import java.math.BigInteger
import java.security.MessageDigest

class MD5Hash : Hash {
    override fun hash(toHash: ByteArray): Int {
        val md5 = MessageDigest.getInstance("MD5")
        val hash = md5.digest(toHash)
        return BigInteger(1, hash).toInt()
    }
}
