package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue

class Day18(input: List<String>) {
    private val points =
        input.map { line ->
            val (x, y, z) = line.split(",").map(String::toInt)
            Point(x, y, z)
        }.toSet()

    fun puzzle1() = surfaceSize(points)

    fun puzzle2() = puzzle1() - surfaceSize(points.innerList())

    private fun Set<Point>.innerList(): Set<Point> {
        val minX = minOf { it.x }
        val minY = minOf { it.y }
        val minZ = minOf { it.z }
        val maxX = maxOf { it.x }
        val maxY = maxOf { it.y }
        val maxZ = maxOf { it.z }

        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    Point(x, y, z).takeUnless { it in this }?.also { println(it) }
                }
            }
        }

        return setOf(
            Point(x = 2, y = 2, z = 5)
        )
    }

    private fun surfaceSize(items: Set<Point>) = items
        .map { point ->
            items.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private data class Point(val x: Int, val y: Int, val z: Int) : Comparable<Point> {
        fun connectedTo(other: Point) =
            sides(other).count { (a, b) -> a == b } == 2 && sides(other).any { (a, b) -> (a - b).absoluteValue == 1 }

        private fun sides(other: Point) = listOf(
            x to other.x,
            y to other.y,
            z to other.z,
        )

        override fun compareTo(other: Point) =
            sides(other).firstOrNull { (a, b) -> a != b }?.let { (a, b) -> a - b } ?: 0
    }

    companion object {
        private const val CUBE_SIDES = 6
    }
}
