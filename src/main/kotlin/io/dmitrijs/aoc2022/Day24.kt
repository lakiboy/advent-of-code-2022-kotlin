package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Direction.DOWN
import io.dmitrijs.aoc2022.Direction.LEFT
import io.dmitrijs.aoc2022.Direction.RIGHT
import io.dmitrijs.aoc2022.Direction.UP

class Day24(input: List<String>) {
    private val grid = input.drop(1).dropLast(1).map { it.drop(1).dropLast(1) }

    fun puzzle1() = Valley(grid).simulate()

    private class Valley(grid: List<String>) {
        private var blizzards: Map<Point, Set<Direction>> =
            grid.flatMapIndexed { y, line ->
                line.mapIndexedNotNull { x, symbol ->
                    symbol.takeUnless { it == SPACE }?.let {
                        Point(x, y) to Direction.of(it)
                    }
                }
            }.toPointsMap()
        private val maxX = grid.first().length - 1
        private val maxY = grid.size - 1
        private val goal = Point(maxX, maxY)

        fun simulate(): Int {
            val start = Point(0, 0)
            val queue = ArrayDeque<Point>().apply { add(start) }
            var moves = 1

            while (queue.isNotEmpty()) {
                blizzards = blizzards.move()
                val point = queue.firstOrNull { it !in blizzards }

                if (point == goal) {
                    return ++moves
                }

                if (point != null) {
                    queue.clear()
                    queue.addAll(
                        point.orthogonalNeighbours()
                            .filterNot { it.outOfBounds }
                            .sortedBy { it.distanceTo(goal) }
                    )
                }

                moves++
            }

            return -1
        }

        override fun toString() =
            (0..maxY).joinToString("\n") { y ->
                (0..maxX).map { x ->
                    val p = Point(x, y)
                    val v = blizzards.getOrDefault(p, emptySet())
                    when {
                        v.isEmpty() -> '.'
                        v.size == 1 -> when (v.single()) {
                            RIGHT -> '>'
                            LEFT -> '<'
                            DOWN -> 'v'
                            UP -> '^'
                        }
                        else -> v.size
                    }
                }.joinToString("")
            }

        private fun List<Pair<Point, Direction>>.toPointsMap() =
            groupBy({ it.first }) { it.second }.mapValues { (_, directions) -> directions.toSet() }

        private fun Map<Point, Set<Direction>>.move() =
            flatMap { (point, directions) ->
                directions.map { direction ->
                    (point + direction).wrap() to direction
                }
            }.toPointsMap()

        private val Point.outOfBounds get() = x < 0 || y < 0 || x > maxX || y > maxY

        private fun Point.wrap() = when {
            x < 0 -> copy(x = maxX)
            x > maxX -> copy(x = 0)
            y < 0 -> copy(y = maxY)
            y > maxY -> copy(y = 0)
            else -> this
        }

        private fun Direction.Companion.of(symbol: Char) = when (symbol) {
            '>' -> RIGHT
            '<' -> LEFT
            'v' -> DOWN
            '^' -> UP
            else -> error("Unsupported direction symbol: $symbol.")
        }
    }

    private companion object {
        const val SPACE = '.'
    }
}
