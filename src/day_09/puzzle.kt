package day_09

import day_09.Direction.*
import println
import readInput
import kotlin.math.absoluteValue

data class Position(private val x: Int, private val y: Int) {
    fun move(dir: Direction) = when (dir) {
        UP -> Position(x, y - 1)
        DOWN -> Position(x, y + 1)
        LEFT -> Position(x - 1, y)
        RIGHT -> Position(x + 1, y)
    }

    fun follow(other: Position): Position {
        val deltaX = other.x - x
        val deltaY = other.y - y

        if (deltaX.absoluteValue <= 1 && deltaY.absoluteValue <= 1) {
            return this
        }

        return when {
            // Horizontal move.
            deltaX == 0 -> if (deltaY > 0) move(DOWN) else move(UP)
            deltaY == 0 -> if (deltaX > 0) move(RIGHT) else move(LEFT)

            // Move to the same line first.
            deltaX == 1 -> move(RIGHT).follow(other)
            deltaX == -1 -> move(LEFT).follow(other)
            deltaY == 1 -> move(DOWN).follow(other)
            deltaY == -1 -> move(UP).follow(other)

            // Diagonal move.
            else -> when {
                deltaX > 0 && deltaY > 0 -> move(RIGHT).move(DOWN)
                deltaX > 0 -> move(RIGHT).move(UP)
                deltaY > 0 -> move(LEFT).move(DOWN)
                else -> move(LEFT).move(UP)
            }
        }
    }
}

private fun MutableList<Position>.follow(head: Position) {
    var node = head

    for (index in indices) {
        node = this[index].follow(node)

        if (node == this[index]) {
            return
        }

        this[index] = node
    }
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun fromAlias(char: Char) = when (char) {
            'U' -> UP
            'D' -> DOWN
            'L' -> LEFT
            'R' -> RIGHT
            else -> error("Invalid character '$char' supplied.")
        }
    }
}

class Motions(size: Int) {
    private var head = Position(0, 0)
    private val items = MutableList(size) { head }
    private val visits = mutableSetOf<Position>()

    val tailVisits get() = visits.size

    fun move(dir: Direction, len: Int) = repeat(len) {
        head = head.move(dir)
        items.follow(head)

        if (items.last() !in visits) {
            visits.add(items.last())
        }
    }
}

fun puzzle(lines: List<String>, size: Int = 1) = Motions(size)
    .apply {
        lines.forEach { line ->
            val (dir, len) = line
            move(Direction.fromAlias(dir), len)
        }
    }
    .tailVisits

private operator fun String.component1() = substringBefore(" ").first()
private operator fun String.component2() = substringAfter(" ").toInt()

fun main() {
    check(puzzle(readInput("day_09/input_test_1"), 1) == 13)
    check(puzzle(readInput("day_09/input_test_1"), 9) == 1)
    check(puzzle(readInput("day_09/input_test_2"), 9) == 36)

    val input = readInput("day_09/input")
    puzzle(input, 1).println()
    puzzle(input, 9).println()
}
