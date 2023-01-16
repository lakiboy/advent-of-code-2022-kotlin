package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue
import kotlin.math.sign

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
        val index = indexOfFirst { it.order == order }
        val value = this[index].value

        val rawIndex = (index + value.sign * (value.absoluteValue % lastIndex)).toInt()
        val newIndex = when {
            rawIndex <= 0 -> size - (rawIndex.absoluteValue + 1)
            rawIndex >= size -> rawIndex % size + 1
            else -> rawIndex
        }

        add(newIndex, removeAt(index))
    }

    private fun <T> List<T>.nth(num: Int) = this[num % size]

    private data class Number(val value: Long, val order: Int)
}
