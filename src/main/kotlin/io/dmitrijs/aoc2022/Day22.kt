package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Day22.Turn.L
import io.dmitrijs.aoc2022.Day22.Turn.R
import io.dmitrijs.aoc2022.Direction.DOWN
import io.dmitrijs.aoc2022.Direction.LEFT
import io.dmitrijs.aoc2022.Direction.RIGHT
import io.dmitrijs.aoc2022.Direction.UP

class Day22(input: String) {
    private val board = Board(input.substringBefore("\n\n").lines())
    private val moves = "$R${input.substringAfter("\n\n").trimEnd()}".toMoves()

    fun puzzle1() = board.run2d(moves).finalPassword

    fun puzzle2() = board.run3d(moves).finalPassword

    private fun String.toMoves() = buildList {
        var index = 0
        do {
            val turn = Turn.valueOf(substring(index, index + 1))
            val steps = substring(++index)
                .takeWhile { it.isDigit() }
                .also { index += it.length }
                .toInt()
            add(Move(turn, steps))
        } while (index < length)
    }

    private class Board(lines: List<String>) {
        private val maxX = lines.maxOf { it.length - 1 }
        private val maxY = lines.size - 1
        private val grid = lines.map { it + " ".repeat(maxX + 1 - it.length) }
        private val start = DirectedPoint(
            point = Point(x = 0, y = 0).fromLeft(),
            direction = UP,
        )

        fun run2d(moves: List<Move>) = moves.fold(start) { cursor, move ->
            cursor.move(move) { wrap2d() }
        }

        fun run3d(moves: List<Move>) = moves.fold(start) { cursor, move ->
            cursor.move(move) { wrap3d() }
        }

        private fun DirectedPoint.move(move: Move, wrap: DirectedPoint.() -> DirectedPoint): DirectedPoint {
            var curr = rotate(move.turn)
            var next = curr
            var step = 0

            do {
                next = next.move().takeUnless { (point) -> point.outside } ?: next.wrap()

                if (next.atWall) {
                    break
                }

                curr = next
            } while (++step < move.steps)

            return curr
        }

        private fun DirectedPoint.wrap2d() = when (direction) {
            RIGHT -> copy(point = point.fromLeft())
            LEFT -> copy(point = point.fromRight())
            UP -> copy(point = point.fromDown())
            DOWN -> copy(point = point.fromUp())
        }

        // Unfinished
        private fun DirectedPoint.wrap3d() = start

        private fun Point.fromLeft() = copy(x = grid[y].indices.first { grid[y][it] != SPACE })

        private fun Point.fromRight() = copy(x = grid[y].indices.last { grid[y][it] != SPACE })

        private fun Point.fromDown() = copy(y = grid.indices.last { grid[it][x] != SPACE })

        private fun Point.fromUp() = copy(y = grid.indices.first { grid[it][x] != SPACE })

        private val DirectedPoint.atWall get() = point.value == WALL

        private val Point.outside get() = x !in 0..maxX || y !in 0..maxY || value == SPACE

        private val Point.value get() = grid[y][x]
    }

    private data class DirectedPoint(val point: Point, val direction: Direction) {
        val finalPassword get() = 1000 * (point.y + 1) + 4 * (point.x + 1) + direction.score

        fun move() = copy(point = point + direction)

        fun rotate(turn: Turn) = copy(
            direction = when (direction to turn) {
                UP to R -> RIGHT
                UP to L -> LEFT
                RIGHT to R -> DOWN
                RIGHT to L -> UP
                DOWN to R -> LEFT
                DOWN to L -> RIGHT
                LEFT to R -> UP
                LEFT to L -> DOWN
                else -> error("Can not rotate $turn from $direction.")
            }
        )

        private val Direction.score get() = when (this) {
            RIGHT -> 0
            DOWN -> 1
            LEFT -> 2
            UP -> 3
        }
    }

    private data class Move(val turn: Turn, val steps: Int)

    private enum class Turn { R, L }

    companion object {
        private const val SPACE = ' '
        private const val WALL = '#'
    }
}
