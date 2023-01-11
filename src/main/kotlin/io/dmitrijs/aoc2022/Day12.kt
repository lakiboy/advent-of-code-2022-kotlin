package io.dmitrijs.aoc2022

class Day12(input: List<String>) {
    private lateinit var start: Point
    private lateinit var finish: Point
    private val grid = input.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            when (char) {
                'S' -> {
                    start = Point(x, y)
                    START
                }
                'E' -> {
                    finish = Point(x, y)
                    FINISH
                }
                else -> char - 'a' + 1
            }
        }
    }

    fun puzzle1() = bfs(start)

    fun puzzle2() =
        buildList {
            grid.forEachIndexed { y, rows ->
                rows.indices.forEach { x ->
                    Point(x, y)
                        .takeIf { p -> p.value == START }
                        ?.let { p -> bfs(p) }
                        ?.let { distance -> add(distance) }
                }
            }
        }.min()

    private val Point.valid get() = x >= 0 && y >= 0 && y <= grid.lastIndex && x <= grid[y].lastIndex

    private val Point.value get() = grid[y][x]

    private val Point.neighbours
        get() = Direction
            .values()
            .map { this + it }
            .filter { to -> to.valid && value + 1 >= to.value }

    private fun bfs(root: Point): Int? {
        val queue = ArrayDeque<Pair<Point, Int>>()
        val visited = mutableSetOf<Point>()

        queue.add(root to 0)

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.removeFirst()

            if (node == finish) {
                return distance
            }

            node.neighbours.filter { it !in visited }.forEach { neighbour ->
                queue.add(neighbour to (distance + 1))
                visited.add(neighbour)
            }
        }

        return null
    }

    companion object {
        private const val START = 1
        private const val FINISH = 26
    }
}
