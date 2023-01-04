package day_15

import println
import readInput
import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int) {
    override fun toString() = "($x; $y)"
}

private operator fun Point.minus(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

private fun MatchResult.toPoint() = Point(
    groupValues[1].toInt(),
    groupValues[2].toInt(),
)

fun puzzle1(input: List<String>, row: Int): Int {
    val ignoreX = hashSetOf<Int>()
    val xRanges = mutableListOf<IntRange>()

    input.forEach { line ->
        val (match1, match2) = """x=(-?\d+), y=(-?\d+)""".toRegex().findAll(line).toList()

        val sensor = match1.toPoint()
        val beacon = match2.toPoint()
        val distance = sensor - beacon
        val radius = if (sensor.y < row) { sensor.y + distance - row } else { row - (sensor.y - distance) }

        if (radius > 0) {
            xRanges += (sensor.x - radius)..(sensor.x + radius)
        }

        if (beacon.y == row) {
            ignoreX += beacon.x
        }
    }

    return xRanges
        .zipWithNext { a, b -> a.toSet() union b.toSet() }
        .flatten()
        .distinct()
        .subtract(ignoreX)
        .size
}

fun main() {
    val testInput = readInput("day_15/input_test")
    check(puzzle1(testInput, 10) == 26)

    val input = readInput("day_15/input")
    puzzle1(input, 2_000_000).println() // 4873353
}
