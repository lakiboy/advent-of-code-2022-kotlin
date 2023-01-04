package day_14

import day_14.Direction.*
import println
import readInput

typealias Path = List<Point>
typealias NextMove = Point.() -> Point

enum class Direction { UP, DOWN, LEFT, RIGHT }

private val moves = listOf<NextMove>(
    { this + DOWN },
    { this + DOWN + LEFT },
    { this + DOWN + RIGHT }
)

data class Point(val x: Int, val y: Int) {
    companion object {
        val Start = Point(500, 0)

        fun of(s: String) = Point(
            s.substringBefore(",").toInt(),
            s.substringAfter(",").toInt(),
        )
    }

    operator fun plus(direction: Direction) = when (direction) {
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
                p += direction
                add(p)
            }
        }
    }
}

class Board(points: Map<Point, Boolean>, private val safe: Boolean) {
    private val maxY = points.keys.maxOf { it.y }.let { if (safe) it + 2 else it }
    private val grid = points.toMutableMap().apply { put(Point.Start, safe) } // For safe +1

    val unitsOfSand by lazy {
        while (true) {
            findSandLocation(Point.Start)?.let { node -> grid[node] = true } ?: break
        }

        grid.values.count { it }
    }

    private fun findSandLocation(root: Point): Point? {
        var node = root
        var prev: Point

        while (true) {
            prev = node
            val next = moves.map { move -> node.move() }

            if (!safe && next.any { it.y > maxY }) {
                return null
            }

            if (next.all { it in grid || (safe && it.y == maxY) }) {
                break
            }

            node = next.first { it !in grid }
        }

        return prev.takeUnless { it == root }
    }
}

private fun Path.toLines() = zipWithNext { a, b -> Line(a, b) }

private fun String.toPath() = split(" -> ").map { Point.of(it) }

private fun createBoard(input: List<String>, safe: Boolean = false): Board {
    val points = input
        .map { s -> s.toPath() }
        .flatMap { path -> path.toLines() }
        .flatMap { line -> line.toPoints() }
        .associateWith { false }

    return Board(points, safe)
}

fun puzzle1(input: List<String>) = createBoard(input).unitsOfSand

fun puzzle2(input: List<String>) = createBoard(input, safe = true).unitsOfSand

fun main() {
    val testInput = readInput("day_14/input_test")
    check(puzzle1(testInput) == 24)
    check(puzzle2(testInput) == 93)

    val input = readInput("day_14/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
