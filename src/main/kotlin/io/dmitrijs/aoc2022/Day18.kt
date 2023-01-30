package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue

class Day18(input: List<String>) {
    private val points =
        input.map { line ->
            val (x, y, z) = line.split(",").map(String::toInt)
            Point(x, y, z)
        }.toSet()

    fun puzzle1() = surfaceSize(points)

    fun puzzle2() = puzzle1() - surfaceSize(points.inner())

    private fun Set<Point>.inner(): Set<Point> {
        val xyGaps = gaps({ it.x to it.y }, { it.z }) { (x, y), z -> Point(x, y, z) }
        val yzGaps = gaps({ it.y to it.z }, { it.x }) { (y, z), x -> Point(x, y, z) }
        val xzGaps = gaps({ it.x to it.z }, { it.y }) { (x, z), y -> Point(x, y, z) }

        /*
        println("=== XY ===")
        xyGaps.onEach { println(it) }
        println("=== YZ ===")
        yzGaps.onEach { println(it) }
        println("=== XZ ===")
        xzGaps.onEach { println(it) }
        */

        return (xyGaps intersect yzGaps intersect xzGaps).onEach { println(it) }
    }

    private fun surfaceSize(items: Set<Point>) = items
        .map { point ->
            items.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private fun Set<Point>.gaps(
        keySelector: (Point) -> Pair<Int, Int>,
        transform: (Point) -> Int,
        factory: (Pair<Int, Int>, Int) -> Point
    ): Set<Point> {
        return groupBy(keySelector)
            .filterValues { points -> points.size > 1 }
            .mapValues { (_, points) ->
                points
                    .map(transform)
                    .sorted()
                    .zipWithNext { a, b -> if (a + 1 != b) (a + 1) until b else null }
                    .filterNotNull()
            }
            .filterValues { points -> points.isNotEmpty() }
            .flatMap { (key, items) ->
                items.flatMap { range ->
                    range.map { factory(key, it) }
                }
            }
            .toSet()
    }

    private data class Point(val x: Int, val y: Int, val z: Int) {
        fun connectedTo(other: Point) =
            sides(other).count { (a, b) -> a == b } == 2 && sides(other).any { (a, b) -> (a - b).absoluteValue == 1 }

        private fun sides(other: Point) = listOf(
            x to other.x,
            y to other.y,
            z to other.z,
        )
    }

    companion object {
        private const val CUBE_SIDES = 6
    }
}
