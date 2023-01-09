package day_09_improved

import println
import readInput
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

data class Position(private val x: Int, private val y: Int) {
    fun follow(other: Position): Position {
        val dx = other.x - x
        val dy = other.y - y

        return max(dx.absoluteValue, dy.absoluteValue)
            .takeIf { it > 1 }
            ?.let { this + Position(dx.sign, dy.sign) }
            ?: return this
    }

    operator fun plus(other: Position) = copy(x = x + other.x, y = y + other.y)
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

enum class Direction(val move: Position) {
    UP(move = Position(0, -1)),
    DOWN(move = Position(0, 1)),
    LEFT(move = Position(-1, 0)),
    RIGHT(move = Position(1, 0));

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
    private val visits = hashSetOf<Position>()

    val tailVisits get() = visits.size

    fun move(dir: Direction, len: Int) = repeat(len) {
        head += dir.move
        items.follow(head)
        visits += items.last()
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
