package io.dmitrijs.aoc2022

class Day18(input: List<String>) {
    private val points =
        input.map { line ->
            val (x, y, z) = line.split(",").map(String::toInt)
            Point(x, y, z)
        }.toSet()

    fun puzzle1() = points.surfaceSize

    fun puzzle2() = points.surfaceSize - points.gaps().surfaceSize

    private val Set<Point>.surfaceSize get() = sumOf { point ->
        CUBE_SIDES - point.neighbours().count { it in this }
    }

    private val Point.lava get() = this in points

    private fun Set<Point>.rangeOf(transform: (Point) -> Int) = minOf(transform) - 1 until maxOf(transform)

    private fun Set<Point>.gaps(): Set<Point> {
        val xRange = rangeOf { it.x }
        val yRange = rangeOf { it.y }
        val zRange = rangeOf { it.z }

        fun Point.edge() = x !in xRange || y !in yRange || z !in zRange

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

                node.neighbours().filterNot { it.lava || it in visited }.forEach {
                    queue.add(it)
                    visited.add(it)
                }
            }

            return true
        }

        return xRange.flatMap { x ->
            yRange.flatMap { y ->
                zRange.map { z -> Point(x, y, z) }
            }.filterNot { it.lava }.filter { it.blocked() }
        }.toSet()
    }

    private fun Point.neighbours() = setOf(
        copy(x = x - 1),
        copy(x = x + 1),
        copy(y = y - 1),
        copy(y = y + 1),
        copy(z = z - 1),
        copy(z = z + 1),
    )

    private data class Point(val x: Int, val y: Int, val z: Int)

    companion object {
        private const val CUBE_SIDES = 6
    }
}
