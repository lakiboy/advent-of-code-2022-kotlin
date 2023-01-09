package io.dmitrijs.aoc2022

class Day01(input: List<String>) {
    private val lines = reduceLines(input)

    fun puzzle1() = lines.max()

    fun puzzle2() = lines.sortedDescending().take(TOP_ELVES).sum()

    private fun reduceLines(lines: List<String>): List<Int> {
        var index = 0

        return lines.fold(mutableListOf(0)) { list, line ->
            if (line.isBlank()) {
                list.add(++index, 0)
            } else {
                list[index] += line.toInt()
            }

            list
        }
    }

    companion object {
        private const val TOP_ELVES = 3
    }
}
