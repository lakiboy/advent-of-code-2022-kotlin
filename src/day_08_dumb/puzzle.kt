package day_08_dumb

// Dumb: no optimization, no cache, no tail recursion.

import println
import readInput

typealias Trees = List<List<Int>>
typealias TreeCoords = Pair<Int, Int>

private fun TreeCoords.top() = Pair(first - 1, second)
private fun TreeCoords.bottom() = Pair(first + 1, second)
private fun TreeCoords.left() = Pair(first, second - 1)
private fun TreeCoords.right() = Pair(first, second + 1)

class TreeGrid private constructor(private val trees: Trees) {
    private val lastIndex = trees.size - 1

    val visibleCount
        get() = (1 until lastIndex).fold(trees.size * 4 - 4) { acc, row ->
            acc + (1 until lastIndex).count { col -> Pair(row, col).isVisible }
        }

    val scenicScore
        get() = trees.indices.maxOf { row ->
            trees.indices.maxOf { col -> Pair(row, col).visibleTrees }
        }

    private val TreeCoords.isEdge get() = first == 0 || second == 0 || first == lastIndex || second == lastIndex

    private operator fun TreeCoords.compareTo(other: TreeCoords) =
        trees[first][second] - trees[other.first][other.second]

    private val TreeCoords.isVisible: Boolean
        get() = when {
            exceedsDirection(this, this) { top() } -> true
            exceedsDirection(this, this) { bottom() } -> true
            exceedsDirection(this, this) { left() } -> true
            exceedsDirection(this, this) { right() } -> true
            else -> false
        }

    private val TreeCoords.visibleTrees
        get(): Int {
            var total = 1

            if (first > 0) {
                total *= visibleDirectionCount(this, this) { top() }
            }
            if (first < lastIndex) {
                total *= visibleDirectionCount(this, this) { bottom() }
            }
            if (second > 0) {
                total *= visibleDirectionCount(this, this) { left() }
            }
            if (second < lastIndex) {
                total *= visibleDirectionCount(this, this) { right() }
            }

            return total
        }

    private fun exceedsDirection(target: TreeCoords, compare: TreeCoords, next: TreeCoords.() -> TreeCoords): Boolean {
        val neighbour = compare.next()

        return if (target > neighbour) {
            return if (neighbour.isEdge) true else exceedsDirection(target, neighbour, next)
        } else {
            false
        }
    }

    private fun visibleDirectionCount(target: TreeCoords, compare: TreeCoords, next: TreeCoords.() -> TreeCoords): Int {
        val neighbour = compare.next()

        if (target <= neighbour || neighbour.isEdge) {
            return 1
        }

        return visibleDirectionCount(target, neighbour, next) + 1
    }

    companion object {
        fun fromLines(lines: List<String>): TreeGrid {
            val trees = lines.map { line ->
                line.map { tree -> tree.digitToInt() }
            }

            return TreeGrid(trees)
        }
    }
}

fun puzzle1(lines: List<String>) = TreeGrid.fromLines(lines).visibleCount

fun puzzle2(lines: List<String>) = TreeGrid.fromLines(lines).scenicScore

fun main() {
    val testInput = readInput("day_08/input_test")
    check(puzzle1(testInput) == 21)
    check(puzzle2(testInput) == 8)

    val input = readInput("day_08/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
