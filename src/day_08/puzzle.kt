package day_08

import println
import readInput

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    operator fun times(distance: Int) = Point(x * distance, y * distance)
}

enum class Direction(val move: Point) {
    UP(move = Point(0, -1)),
    DOWN(move = Point(0, 1)),
    LEFT(move = Point(-1, 0)),
    RIGHT(move = Point(1, 0))
}

class Grid private constructor(private val trees: List<List<Int>>) {
    private val len = trees.size

    val visibleCount
        get() = (len * 4 - 4) + (1 until (len - 1)).sumOf { y ->
            (1 until (len - 1)).count { x -> Point(x, y).visible }
        }

    private val Point.visible get() = Direction.values().any { visibleTowards(it.move, 1) }

    private val Point.value get() = trees[y][x]

    private val Point.edge get() = x == 0 || y == 0 || x == len - 1 || y == len - 1

    private tailrec fun Point.visibleTowards(move: Point, distance: Int): Boolean {
        val neighbour = move * distance + this

        return when {
            neighbour.value >= value -> false
            neighbour.edge -> true
            else -> visibleTowards(move, distance + 1)
        }
    }

    companion object {
        fun of(lines: List<String>): Grid {
            val trees = lines.map { row ->
                row.map { it.digitToInt() }
            }

            return Grid(trees)
        }
    }
}

fun puzzle1(lines: List<String>) = Grid.of(lines).visibleCount

fun main() {
    val testInput = readInput("day_08/input_test")
    check(puzzle1(testInput) == 21)

    val input = readInput("day_08/input")
    puzzle1(input).println()
}
