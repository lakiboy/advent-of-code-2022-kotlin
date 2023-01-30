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

    @Suppress("NestedBlockDepth")
    private fun Set<Point>.inner(): Set<Point> {
        val minX = minOf { it.x }
        val minY = minOf { it.y }
        val minZ = minOf { it.z }
        val maxX = maxOf { it.x }
        val maxY = maxOf { it.y }
        val maxZ = maxOf { it.z }

        val innerPoints = mutableSetOf<Point>()

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    Point(x, y, z)
                        .takeUnless { it in this }
                        ?.takeIf { xGap(it) || yGap(it) || zGap(it) }
                        ?.let { innerPoints.add(it) }
                }
            }
        }

        return innerPoints.onEach { println(it) }
    }

    private fun surfaceSize(items: Set<Point>) = items
        .map { point ->
            items.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private fun Set<Point>.xGap(other: Point) =
        any { it.x + 1 == other.x && it.y == other.y && it.z == other.z } &&
            any { it.x - 1 == other.x && it.y == other.y && it.z == other.z }

    private fun Set<Point>.yGap(other: Point) =
        any { it.x == other.x && it.y + 1 == other.y && it.z == other.z } &&
            any { it.x == other.x && it.y - 1 == other.y && it.z == other.z }

    private fun Set<Point>.zGap(other: Point) =
        any { it.x == other.x && it.y == other.y && it.z + 1 == other.z } &&
            any { it.x == other.x && it.y == other.y && it.z - 1 == other.z }

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
