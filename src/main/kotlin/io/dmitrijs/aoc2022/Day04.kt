package io.dmitrijs.aoc2022

typealias SpaceSection = Pair<Int, Int>

class Day04(private val input: List<String>) {
    fun puzzle1() = input.count {
        compare(it) { one, two -> one in two || two in one }
    }

    fun puzzle2() = input.count {
        compare(it) { one, two -> one intersects two || two intersects one }
    }

    private fun compare(line: String, fn: (SpaceSection, SpaceSection) -> Boolean): Boolean {
        val (one, two) = line
            .split(",")
            .map { it.substringBefore("-").toInt() to it.substringAfter("-").toInt() }

        return fn(one, two)
    }

    private operator fun SpaceSection.contains(other: SpaceSection) = first <= other.first && second >= other.second

    private operator fun SpaceSection.contains(number: Int) = number in first..second

    private infix fun SpaceSection.intersects(other: SpaceSection) = first in other || second in other
}
