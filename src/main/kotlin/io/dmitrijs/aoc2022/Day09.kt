package io.dmitrijs.aoc2022

import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

class Day09(private val input: List<String>) {
    fun puzzle1() = solve(size = 1)

    fun puzzle2() = solve(size = 9)

    private fun solve(size: Int) = Motions(size)
        .apply {
            input.forEach { line ->
                val (dir, len) = line
                move(Direction.of(dir), len)
            }
        }
        .tailVisits

    private fun Direction.Companion.of(char: Char) = when (char) {
        'U' -> Direction.UP
        'D' -> Direction.DOWN
        'L' -> Direction.LEFT
        'R' -> Direction.RIGHT
        else -> error("Invalid character '$char' supplied.")
    }

    private operator fun String.component1() = substringBefore(" ").first()

    private operator fun String.component2() = substringAfter(" ").toInt()

    private class Motions(size: Int) {
        private var head = Point(0, 0)
        private val items = MutableList(size) { head }
        private val visits = hashSetOf<Point>()

        val tailVisits get() = visits.size

        fun move(dir: Direction, len: Int) = repeat(len) {
            head += dir.move
            items.follow(head)
            visits += items.last()
        }

        private fun Point.follow(other: Point): Point {
            val dx = other.x - x
            val dy = other.y - y

            return max(dx.absoluteValue, dy.absoluteValue)
                .takeIf { it > 1 }
                ?.let { this + Point(dx.sign, dy.sign) }
                ?: return this
        }

        private fun MutableList<Point>.follow(head: Point) {
            var node = head

            for (index in indices) {
                node = this[index].follow(node)

                if (node == this[index]) {
                    return
                }

                this[index] = node
            }
        }
    }
}
