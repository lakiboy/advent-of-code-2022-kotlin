package day_12

import day_12.Direction.*
import println
import readInput
import kotlin.math.absoluteValue

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Point(val x: Int, val y: Int) {
    fun move(direction: Direction) = when (direction) {
        UP -> Point(x, y - 1)
        DOWN -> Point(x, y + 1)
        RIGHT -> Point(x + 1, y)
        LEFT -> Point(x - 1, y)
    }
}

class HeightMap(lines: List<String>) {
    private lateinit var start: Point
    private lateinit var finish: Point
    private val grid = lines.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            when (char) {
                'S' -> {
                    start = Point(x, y)
                    1
                }
                'E' -> {
                    finish = Point(x, y)
                    26
                }
                else -> char - 'a' + 1
            }
        }
    }
    private val visited = mutableSetOf<Point>()

    var distance = 0
        private set

    init {
        dfs(start, 0)
    }

    private val Point.valid get() = x >= 0 && y >= 0 && y <= grid.lastIndex && x <= grid[y].lastIndex

    private val Point.neighbours get() = Direction
        .values()
        .map { move(it) }
        .filter { to -> to.valid }
        .filter { to -> to !in visited }
        .filter { to -> grid[y][x] + 1 >= grid[to.y][to.x] }
        .sortedBy { to -> (to.x - finish.x).absoluteValue + (to.y - finish.y).absoluteValue - grid[to.y][to.x] }

    private fun dfs(node: Point, depth: Int) {
        visited.add(node)

        if (node == finish) {
            distance = depth
        } else {
            node.neighbours.forEach { neighbour -> dfs(neighbour, depth + 1) }
        }
    }
}

fun puzzle1(lines: List<String>) = HeightMap(lines).distance

fun main() {
    val testInput = readInput("day_12/input_test")
    check(puzzle1(testInput) == 31)

    val input = readInput("day_12/input")
    puzzle1(input).println()
}
