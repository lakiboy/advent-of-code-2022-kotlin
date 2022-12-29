package day_01

import println
import readInput

private fun reduceLines(lines: List<String>): List<Int> {
    var index = 0

    return lines.fold(mutableListOf(0)) { list, line ->
        if (line.isBlank()) {
            list.add(++index, 0)
        } else {
            list[index] += line.toInt()
        }

        list
    }
}

fun puzzle1(input: List<String>) = reduceLines(input).max()

fun puzzle2(input: List<String>) = reduceLines(input)
    .sortedDescending()
    .take(3)
    .sum()

fun main() {
    val testInput = readInput("day_01/input_test")
    check(puzzle1(testInput) == 24_000)
    check(puzzle2(testInput) == 45_000)

    val input = readInput("day_01/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
