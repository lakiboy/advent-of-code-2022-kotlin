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

    private fun surfaceSize(items: Set<Point3d>) = items
        .map { point ->
            items.count { adjacent -> adjacent.connectedTo(point) }
        }
        .sumOf { commonSides -> CUBE_SIDES - commonSides }

    private fun Set<Point3d>.gaps(): Set<Point3d> {
        val minX = minOf { it.x }
        val maxX = maxOf { it.x }
        val minY = minOf { it.y }
        val maxY = maxOf { it.y }
        val minZ = minOf { it.z }
        val maxZ = maxOf { it.z }
        val lava = this

        fun Point3d.lava() = this in lava

        fun Point3d.edge() = x <= minX || x >= maxX || y <= minY || y >= maxY || z <= minZ || z >= maxZ

        fun Point3d.blocked(): Boolean {
            val queue = ArrayDeque<Point3d>()
            val visited = hashSetOf<Point3d>()

            queue.add(this)
            visited.add(this)

            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()

                if (node.edge()) {
                    return false
                }

                node.neighbours().filterNot { it.lava() || it in visited }.forEach {
                    queue.add(it)
                    visited.add(it)
                }
            }

            return true
        }

        return (minX..maxX)
            .flatMap { x ->
                (minY..maxY).flatMap { y ->
                    (minZ..maxZ).map { z ->
                        Point3d(x, y, z)
                    }
                }.filterNot { it.lava() || it.edge() }.filter { it.blocked() }
            }.toSet()
    }

    private fun Point3d.neighbours() = Direction.values().map { direction ->
        copy(
            x = x + direction.move.x,
            y = y + direction.move.y,
            z = z + direction.move.z,
        )
    }

    private data class Point3d(val x: Int, val y: Int, val z: Int) {
        fun connectedTo(other: Point3d) =
            sides(other).count { (a, b) -> a == b } == 2 && sides(other).any { (a, b) -> (a - b).absoluteValue == 1 }

        private fun sides(other: Point3d) = listOf(
            x to other.x,
            y to other.y,
            z to other.z,
        )
    }

    private enum class Direction(val move: Point3d) {
        UP(move = Point3d(0, -1, 0)),
        DOWN(move = Point3d(0, 1, 0)),
        LEFT(move = Point3d(-1, 0, 0)),
        RIGHT(move = Point3d(1, 0, 0)),
        TOP(move = Point3d(0, 0, -1)),
        BOTTOM(move = Point3d(0, 0, 1));
    }

    companion object {
        private const val CUBE_SIDES = 6
    }
}
