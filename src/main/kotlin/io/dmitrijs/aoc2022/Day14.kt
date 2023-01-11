package io.dmitrijs.aoc2022

class Day14(input: List<String>) {
    private val points = input
        .map { s -> s.toPath() }
        .flatMap { path -> path.toLines() }
        .flatMap { line -> line.toPoints() }
        .associateWith { false }

    fun puzzle1() = Board().unitsOfSand()

    fun puzzle2() = Board(safe = true).unitsOfSand()

    private fun List<Point>.toLines() = zipWithNext { a, b -> Line(a, b) }

    private fun String.toPath() = split(" -> ").map { Point.of(it) }

    private data class Line(private val a: Point, private val b: Point) {
        fun toPoints(): List<Point> {
            val direction = when {
                a.y == b.y -> if (a.x > b.x) Direction.LEFT else Direction.RIGHT
                a.x == b.x -> if (a.y > b.y) Direction.UP else Direction.DOWN
                else -> error("Invalid line.")
            }

            return buildList {
                var p = a
                add(a)

                while (p != b) {
                    p += direction
                    add(p)
                }
            }
        }
    }

    private inner class Board(private val safe: Boolean = false) {
        private val maxY = points.keys.maxOf { it.y }.let { if (safe) it + 2 else it }
        private val grid = points.toMutableMap().apply { put(start, safe) } // For safe +1

        fun unitsOfSand(): Int {
            while (true) {
                findSandLocation()?.let { node -> grid[node] = true } ?: break
            }

            return grid.values.count { it }
        }

        private fun findSandLocation(): Point? {
            var node = start
            var prev: Point

            while (true) {
                prev = node
                val next = moves.map { move -> node.move() }

                // Falls to abyss unless safe.
                if (!safe && next.any { it.y > maxY }) {
                    return null
                }

                // Can not move further.
                if (next.all { it in grid || (safe && it.y == maxY) }) {
                    break
                }

                node = next.first { it !in grid }
            }

            return prev.takeUnless { it == start }
        }
    }

    companion object {
        private val start = Point(500, 0)
        private val moves = listOf<Point.() -> Point>(
            { this + Direction.DOWN },
            { this + Direction.DOWN + Direction.LEFT },
            { this + Direction.DOWN + Direction.RIGHT }
        )
    }
}
