package io.dmitrijs.aoc2022

class Day03(private val input: List<String>) {
    private val priorityMap = buildMap {
        ('a'..'z').mapIndexed { index, char ->
            set(char, index + 1)
            set(char.uppercaseChar(), index + 27)
        }
    }

    private val Char.priority get() = priorityMap[this] ?: 0

    fun puzzle1() = input.sumOf { findUniqueElement(it).priority }

    fun puzzle2() = input.chunked(3).sumOf { findBadgeElement(it).priority }

    private fun findUniqueElement(line: String): Char {
        val pos = line.length / 2
        val one = line.substring(0, pos)
        val two = line.substring(pos)

        return (one intersect two).first()
    }

    private fun findBadgeElement(lines: List<String>): Char {
        val one = lines[0] intersect lines[1]
        val two = lines[1] intersect lines[2]

        return (one intersect two).first()
    }

    private infix fun String.intersect(other: String) = toSet() intersect other.toSet()
}
