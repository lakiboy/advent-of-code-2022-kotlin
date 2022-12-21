import MoveDirection.*
import kotlin.math.absoluteValue

private enum class MoveDirection { UP, DOWN, LEFT, RIGHT }

private data class Coordinates(val x: Int, val y: Int) {
    fun move(direction: MoveDirection) = when (direction) {
        UP -> Coordinates(x, y - 1)
        DOWN -> Coordinates(x, y + 1)
        RIGHT -> Coordinates(x + 1, y)
        LEFT -> Coordinates(x - 1, y)
    }
}

private class HeightMap(lines: List<String>) {
    private lateinit var cursor: Coordinates
    private lateinit var finish: Coordinates
    private val grid = lines.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            when (char) {
                'S' -> {
                    cursor = Coordinates(x, y)
                    1
                }

                'E' -> {
                    finish = Coordinates(x, y)
                    26
                }

                else -> char - 'a' + 1
            }
        }
    }
    private val visited = mutableSetOf<Coordinates>()

    var steps = 0

    init {
        while (cursor != finish) {
            visited.add(cursor)
            cursor = cursor
                .bestMoves(finish)
                .first { direction -> cursor.canMoveTowards(direction) }
                .let { direction -> cursor.move(direction) }
                .also { steps++ }
        }
    }

    private fun Coordinates.canMoveTowards(direction: MoveDirection) = move(direction)
        .takeUnless { it.invalid }
        ?.let { to -> grid[y][x] + 1 >= grid[to.y][to.x] }
        ?: false

    private val Coordinates.invalid get() = this in visited || x < 0 || y < 0 || y > grid.lastIndex || x > grid[y].lastIndex

    private fun Coordinates.bestMoves(other: Coordinates): List<MoveDirection> {
        val dx = other.x - x
        val dy = other.y - y

        return buildList {
            if (dx.absoluteValue > dy.absoluteValue) {
                add(if (dx > 0) RIGHT else LEFT)
                add(if (dy > 0) DOWN else UP)
                add(if (dy > 0) UP else DOWN)
                add(if (dx > 0) LEFT else RIGHT)
            } else {
                add(if (dy > 0) DOWN else UP)
                add(if (dx > 0) RIGHT else LEFT)
                add(if (dx > 0) LEFT else RIGHT)
                add(if (dy > 0) UP else DOWN)
            }
        }
    }
}

private fun puzzle1(lines: List<String>) = HeightMap(lines).steps

fun main() {
    val testInput = readInput("day_12_input_test")
    check(puzzle1(testInput) == 31)

    val input = readInput("day_12_input")
    puzzle1(input).println()
}
