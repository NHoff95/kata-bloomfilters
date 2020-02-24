package de.nhoff95.kata.bloomfilters.model

class Bitmap(val size: Int) {
    private val hashMap: MutableMap<Int, Boolean> = HashMap<Int, Boolean>(size).withDefault { false }

    /**
     * Returns the number of bits set to true.
     */
    fun cardinality() = hashMap.values.count { it }

    /**
     * Sets the specified position to true
     * @throws IllegalArgumentException If position is invalid
     */
    fun set(position: Int) = when {
        position >= size -> throw IllegalArgumentException("Position is larger than Bitmap size.")
        position < 0 -> throw IllegalArgumentException("Position cannot be negative.")
        else -> hashMap[position] = true
    }

    /**
     * Returns true, if the specified position was already set, otherwise false
     */
    fun isSet(position: Int) = hashMap.getOrDefault(position, false)
}
