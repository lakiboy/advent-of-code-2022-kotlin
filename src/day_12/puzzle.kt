package day_12

import day_12.Direction.*
import println
import readInput

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Point(val x: Int, val y: Int) {
    fun move(direction: Direction) = when (direction) {
        UP -> Point(x, y - 1)
        DOWN -> Point(x, y + 1)
        RIGHT -> Point(x + 1, y)
        LEFT -> Point(x - 1, y)
    }
}

private const val START = 1
private const val FINISH = 26

class HeightMap(lines: List<String>) {
    private lateinit var start: Point
    private lateinit var finish: Point
    private val grid = lines.mapIndexed { y, line ->
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

    val distance get() = bfs(start)

    val bestDistance
        get() =
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
            .map { move(it) }
            .filter { to -> to.valid && value + 1 >= to.value }

    private fun bfs(root: Point): Int? {
        val queue = mutableListOf<Pair<Point, Int>>()
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
}

fun puzzle1(lines: List<String>) = HeightMap(lines).distance

fun puzzle2(lines: List<String>) = HeightMap(lines).bestDistance

fun main() {
    val testInput = readInput("day_12/input_test")
    check(puzzle1(testInput) == 31)
    check(puzzle2(testInput) == 29)

    val input = readInput("day_12/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
