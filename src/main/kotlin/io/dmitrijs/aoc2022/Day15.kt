package io.dmitrijs.aoc2022

import kotlin.math.max

class Day15(input: List<String>) {
    private val pairs = input.map {
        """x=(-?\d+), y=(-?\d+)""".toRegex()
            .findAll(it)
            .toList()
            .let { (match1, match2) -> Signal(match1.toPoint(), match2.toPoint()) }
    }

    fun puzzle1(row: Int) = takenRanges(row)
        .sortedBy { it.first }
        .reduce { acc, next -> acc merge next }
        .let { (a, b) -> b - a }

    fun puzzle2(limit: Int): Long {
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

    private fun MatchResult.toPoint() = Point(
        groupValues[1].toInt(),
        groupValues[2].toInt(),
    )

    private infix fun Pair<Int, Int>.merge(other: Pair<Int, Int>) = first to max(second, other.second)

    private fun takenRanges(row: Int) = buildList {
        pairs.forEach { signal ->
            val radius = signal.rowRadius(row)
            if (radius > 0) {
                this += (signal.sensor.x - radius) to (signal.sensor.x + radius)
            }
        }
    }

    private data class Signal(val sensor: Point, val beacon: Point) {
        private val distance get() = sensor.distanceTo(beacon)

        fun rowRadius(row: Int) = if (sensor.y < row) {
            sensor.y + distance - row
        } else {
            row - (sensor.y - distance)
        }
    }
}
