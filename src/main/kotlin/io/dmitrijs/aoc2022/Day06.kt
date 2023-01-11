package io.dmitrijs.aoc2022

class Day06(private val input: String) {
    fun puzzle1() = solve(length = 4)

    fun puzzle2() = solve(length = 14)

    private fun solve(length: Int) = (0 until input.length - length)
        .first { index -> input.substring(index, index + length).toSet().size == length } + length
}
