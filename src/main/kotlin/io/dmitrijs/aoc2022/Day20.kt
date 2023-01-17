package io.dmitrijs.aoc2022

class Day20(private val input: List<Long>) {
    fun puzzle1() = decipher()

    fun puzzle2() = decipher(decryptionKey = 811_589_153L, rounds = 10)

    private fun decipher(decryptionKey: Long = 1L, rounds: Int = 1): Long {
        val cypher = input
            .mapIndexed { order, value -> Number(value * decryptionKey, order) }
            .toMutableList()

        repeat(rounds) {
            input.indices.forEach { order -> cypher.mix(order) }
        }

        val zeroIndex = cypher.zeroIndex()

        return listOf(1000, 2000, 3000).sumOf { cypher.nth(zeroIndex + it).value }
    }

    private fun List<Number>.zeroIndex() = indexOfFirst { it.value == 0L }

    private fun MutableList<Number>.mix(order: Int) {
        val oldIndex = indexOfFirst { it.order == order }
        val newIndex = (oldIndex + this[oldIndex].value).mod(size - 1)
        add(newIndex, removeAt(oldIndex))
    }

    private fun <T> List<T>.nth(num: Int) = this[num % size]

    private data class Number(val value: Long, val order: Int)
}
