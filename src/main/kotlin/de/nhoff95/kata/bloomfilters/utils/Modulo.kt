package de.nhoff95.kata.bloomfilters.utils

/**
 * Calculates the modulo of two numbers. It returns always the positive representation
 */
fun modulo(original: Int, range: Int) = (((original % range).toLong() + range.toLong()) % range).toInt()
