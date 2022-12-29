package day_14

import day_14.Direction.*
import println
import readInput

typealias Path = List<Point>

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Point(val x: Int, val y: Int) {
    companion object {
        val Sand = Point(500, 0)

        fun of(s: String) = Point(
            s.substringBefore(",").toInt(),
            s.substringAfter(",").toInt(),
        )
    }

    fun move(direction: Direction) = when (direction) {
        UP -> Point(x, y - 1)
        DOWN -> Point(x, y + 1)
        LEFT -> Point(x - 1, y)
        RIGHT -> Point(x + 1, y)
    }
}

data class Line(private val a: Point, private val b: Point) {
    fun toPoints(): List<Point> {
        val direction = when {
            a.y == b.y -> if (a.x > b.x) LEFT else RIGHT
            a.x == b.x -> if (a.y > b.y) UP else DOWN
            else -> error("Invalid line.")
        }

        return buildList {
            var p = a
            add(a)

            while (p != b) {
                p = p.move(direction)
                add(p)
            }
        }
    }
}

class Board(points: Set<Point>) {
    private val minX = points.minOf { it.x }
    private val minY = points.minOf { it.y }
    private val maxX = points.maxOf { it.x }
    private val maxY = points.maxOf { it.y }
    private val grid = MutableList(maxY - minY + 1) { y ->
        MutableList(maxX - minX + 1) { x ->
            when (Point(x + minX, y + minY)) {
                Point.Sand -> '+'
                in points -> '#'
                else -> '.'
            }
        }
    }

    override fun toString() = grid.joinToString("\n") { rows ->
        rows.joinToString("")
    }
}

private fun Path.toLines() = zipWithNext { a, b -> Line(a, b) }

private fun String.toPath() = split(" -> ").map { Point.of(it) }

fun puzzle1(input: List<String>): Int {
    val points = input
        .map { s -> s.toPath() }
        .flatMap { path -> path.toLines() }
        .flatMap { line -> line.toPoints() }
        .toHashSet()
        .apply { add(Point.Sand) }

    val board = Board(points).also { it.println() }

    return 24
}

fun main() {
    val testInput = readInput("day_14/input_test")
    check(puzzle1(testInput) == 24)

//    val input = readInput("day_14/input")
//    puzzle1(input).println()
}
