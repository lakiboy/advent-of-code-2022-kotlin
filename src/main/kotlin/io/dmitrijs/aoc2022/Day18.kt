package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue

class Day18(input: List<String>) {
    private val points = input.map { line ->
        val (x, y, z) = line.split(",").map(String::toInt)
        Point(x, y, z)
    }

    fun puzzle1() = points
        .map { point ->
            points.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private data class Point(private val x: Int, private val y: Int, private val z: Int) {
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
