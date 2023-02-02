package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue

class Day18(input: List<String>) {
    private val points =
        input.map { line ->
            val (x, y, z) = line.split(",").map(String::toInt)
            Point3d(x, y, z)
        }.toSet()

    fun puzzle1() = surfaceSize(points)

    fun puzzle2() = puzzle1() - surfaceSize(points.gaps())

    private fun Set<Point3d>.gaps(): Set<Point3d> {
        val xyGaps = flatGaps({ it.z }) { Point(it.x, it.y) }
        val yzGaps = flatGaps({ it.x }) { Point(it.y, it.z) }.map { Point3d(it.z, it.x, it.y) }.toSet()
        val xzGaps = flatGaps({ it.y }) { Point(it.x, it.z) }.map { Point3d(it.x, it.z, it.y) }.toSet()

        return (xyGaps intersect yzGaps intersect xzGaps)
    }

    private fun surfaceSize(items: Set<Point3d>) = items
        .map { point ->
            items.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private fun Set<Point3d>.flatGaps(keySelector: (Point3d) -> Int, transform: (Point3d) -> Point) =
        groupBy(keySelector, transform).flatMap { (z, flat) ->
            val minX = flat.minOf { it.x }
            val maxX = flat.maxOf { it.x }
            val minY = flat.minOf { it.y }
            val maxY = flat.maxOf { it.y }

            fun Point.edge() = x <= minX || x >= maxX || y <= minY || y >= maxY

            fun Point.blocked(): Boolean {
                val queue = ArrayDeque<Point>()
                val visited = hashSetOf<Point>()

                queue.add(this)
                visited.add(this)

                while (queue.isNotEmpty()) {
                    val node = queue.removeFirst()

                    if (node.edge()) {
                        return false
                    }

                    neighbours().filterNot { it in flat }.filterNot { it in visited }.forEach {
                        visited.add(it)
                        queue.add(it)
                    }
                }

                return true
            }

            (minX..maxX)
                .flatMap { x -> (minY..maxY).map { y -> Point(x, y) } }
                .filterNot { it in flat || it.edge() }
                .filter { it.blocked() }
                .map { point -> point.to3d(z) }
        }.toSet()

    private fun Point.to3d(z: Int) = Point3d(x, y, z)

    private fun Point.neighbours() = Direction.values().map { this + it }

    private data class Point3d(val x: Int, val y: Int, val z: Int) {
        fun connectedTo(other: Point3d) =
            sides(other).count { (a, b) -> a == b } == 2 && sides(other).any { (a, b) -> (a - b).absoluteValue == 1 }

        private fun sides(other: Point3d) = listOf(
            x to other.x,
            y to other.y,
            z to other.z,
        )
    }

    companion object {
        private const val CUBE_SIDES = 6
    }
}
