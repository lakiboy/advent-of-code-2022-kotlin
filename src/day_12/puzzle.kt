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

    private val Point.valid get() = x >= 0 && y >= 0 && y <= grid.lastIndex && x <= grid[y].lastIndex

    private val Point.neighbours get() = Direction
        .values()
        .map { move(it) }
        .filter { to -> to.valid && to !in visited && grid[y][x] + 1 >= grid[to.y][to.x] }

    val distance get(): Int {
        val queue = mutableListOf<Pair<Point, Int>>()
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.removeFirst()

            if (node == finish) {
                return distance
            }

            node.neighbours.forEach { neighbour ->
                queue.add(neighbour to (distance + 1))
                visited.add(neighbour)
            }
        }

        return -1
    }
}

fun puzzle1(lines: List<String>) = HeightMap(lines).distance

fun main() {
    val testInput = readInput("day_12/input_test")
    check(puzzle1(testInput) == 31)

    val input = readInput("day_12/input")
    puzzle1(input).println()
}
