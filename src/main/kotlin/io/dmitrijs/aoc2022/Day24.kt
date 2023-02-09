package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Direction.DOWN
import io.dmitrijs.aoc2022.Direction.LEFT
import io.dmitrijs.aoc2022.Direction.RIGHT
import io.dmitrijs.aoc2022.Direction.UP

class Day24(input: List<String>) {
    private val grid = input.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }
    private val maxX = grid.first().length - 1
    private val maxY = grid.size - 1
    private val loop = Pair(maxX + 1, maxY + 1).lcm()

    // Greedy, but simple.
    private val blizzardsInTime = blizzardPositions(loop)

    fun puzzle1() = bfs(Point(0, -1).inTime(0), Point(maxX, maxY))

    fun puzzle2(): Int {
        val t1 = bfs(Point(0, -1).inTime(0), Point(maxX, maxY))
        val t2 = bfs(Point(maxX, maxY + 1).inTime(t1), Point(0, 0))
        val t3 = bfs(Point(0, -1).inTime(t1 + t2), Point(maxX, maxY))

        return t1 + t2 + t3
    }

    private fun bfs(start: PointInTime, goal: Point): Int {
        val queue = ArrayDeque(listOf(start to 0))
        val visited = hashSetOf<PointInTime>()

        while (queue.isNotEmpty()) {
            val (pit, taken) = queue.removeFirst()
            val (expedition, time) = pit

            if (expedition == goal) {
                return taken + 1
            }

            val positions = expedition.orthogonalNeighbours().filterNot { it.outOfBounds }

            (positions + expedition)
                .map { it.inTime(time + 1) }
                .filterNot { it in visited || it in blizzardsInTime }
                .forEach {
                    visited.add(it)
                    queue.add(it to taken + 1)
                }
        }

        return -1
    }

    private fun blizzardPositions(t: Int) = grid.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, symbol ->
            symbol.takeUnless { it == SPACE }?.let {
                var p = Point(x, y)
                val d = Direction.of(it)
                (0 until t).map { time ->
                    p.inTime(time).also { p = (p + d).wrap() }
                }
            }
        }.flatten()
    }.toSet()

    private fun Pair<Int, Int>.lcm(): Int {
        var lcm = if (first > second) first else second

        while (true) {
            if (lcm % first == 0 && lcm % second == 0) {
                return lcm
            }
            lcm++
        }
    }

    private val Point.outOfBounds get() = x < 0 || y < 0 || x > maxX || y > maxY

    private fun Point.wrap() = when {
        x < 0 -> copy(x = maxX)
        x > maxX -> copy(x = 0)
        y < 0 -> copy(y = maxY)
        y > maxY -> copy(y = 0)
        else -> this
    }

    private fun Point.inTime(t: Int) = PointInTime(this, t % loop)

    private fun Direction.Companion.of(symbol: Char) = when (symbol) {
        '>' -> RIGHT
        '<' -> LEFT
        'v' -> DOWN
        '^' -> UP
        else -> error("Unsupported direction symbol: $symbol.")
    }

    private data class PointInTime(val p: Point, val t: Int)

    private companion object {
        const val SPACE = '.'
    }
}
