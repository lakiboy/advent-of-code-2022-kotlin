package io.dmitrijs.aoc2022

class Day08(input: List<String>) {
    private val size = input.size
    private val grid = input.map { row ->
        row.map { it.digitToInt() }
    }

    fun puzzle1() = (size * 4 - 4) + (1 until (size - 1)).sumOf { y ->
        (1 until (size - 1)).count { x -> Point(x, y).visible }
    }

    fun puzzle2() = (0 until size).maxOf { y ->
        (0 until size).maxOf { x -> Point(x, y).visibleTrees }
    }

    private val Point.value get() = grid[y][x]

    private val Point.edge get() = x == 0 || y == 0 || x == size - 1 || y == size - 1

    private val Point.invalid get() = x !in 0 until size || y !in 0 until size

    private val Point.visible get() = Direction.values().any { direction ->
        visibleTowards(direction.move, 1)
    }

    private val Point.visibleTrees get() = Direction.values().fold(1) { acc, direction ->
        acc * visibleCount(direction.move, 1)
    }

    private tailrec fun Point.visibleTowards(move: Point, distance: Int): Boolean {
        val neighbour = move * distance + this

        return when {
            neighbour.value >= value -> false
            neighbour.edge -> true
            else -> visibleTowards(move, distance + 1)
        }
    }

    private tailrec fun Point.visibleCount(move: Point, distance: Int): Int {
        val neighbour = move * distance + this

        return when {
            neighbour.invalid -> distance - 1
            neighbour.value >= value -> distance
            else -> visibleCount(move, distance + 1)
        }
    }
}
