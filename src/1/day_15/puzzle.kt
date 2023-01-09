package day_15

import println
import readInput
import kotlin.math.absoluteValue
import kotlin.math.max

data class Point(val x: Int, val y: Int)

data class Signal(val sensor: Point, val beacon: Point) {
    val distance by lazy { sensor - beacon }
}

private operator fun Point.minus(other: Point) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

private infix fun Pair<Int, Int>.merge(other: Pair<Int, Int>) = first to max(second, other.second)

class Grid(private val pairs: List<Signal>) {
    fun takenPositions(row: Int) = takenRanges(row)
        .sortedBy { it.first }
        .reduce { acc, next -> acc merge next }
        .let { (a, b) -> b - a }

    fun tuningFrequency(limit: Int): Long {
        for (row in 0..limit) {
            val ranges = takenRanges(row)
                .sortedBy { it.first }
                .map { (a, b) -> a.coerceIn(0, limit) to b.coerceIn(0, limit) }

            var fullRange = ranges.first()
            for (i in 1 until ranges.size) {
                val col = fullRange.second + 1

                // Lookup for gap
                if (col < ranges[i].first) {
                    return 4_000_000L * col + row
                }

                fullRange = fullRange merge ranges[i]
            }
        }

        return -1
    }

    private fun takenRanges(row: Int) = buildList {
        pairs.forEach { signal ->
            val radius = signal.rowRadius(row)
            if (radius > 0) {
                this += (signal.sensor.x - radius) to (signal.sensor.x + radius)
            }
        }
    }

    private fun Signal.rowRadius(row: Int) = if (sensor.y < row) {
        sensor.y + distance - row
    } else {
        row - (sensor.y - distance)
    }
}

private fun MatchResult.toPoint() = Point(
    groupValues[1].toInt(),
    groupValues[2].toInt(),
)

private fun List<String>.toPairs() = map {
    """x=(-?\d+), y=(-?\d+)""".toRegex()
        .findAll(it)
        .toList()
        .let { (match1, match2) -> Signal(match1.toPoint(), match2.toPoint()) }
}

fun puzzle1(input: List<String>, row: Int) = Grid(input.toPairs()).takenPositions(row)

fun puzzle2(input: List<String>, limit: Int) = Grid(input.toPairs()).tuningFrequency(limit)

fun main() {
    val testInput = readInput("day_15/input_test")
    check(puzzle1(testInput, 10) == 26)
    check(puzzle2(testInput, 20) == 56_000_011L)

    val input = readInput("day_15/input")
    puzzle1(input, 2_000_000).println()
    puzzle2(input, 4_000_000).println()
}
