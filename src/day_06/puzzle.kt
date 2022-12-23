package day_06

import println
import readText

// Ineffective, but single expression :)
fun puzzle(input: String, len: Int) = (0 until input.length - len)
    .first { index -> input.substring(index, index + len).toSet().size == len } + len

fun main() {
    check(puzzle(readText("day_06/input_test_1"), 4) == 5)
    check(puzzle(readText("day_06/input_test_2"), 4) == 6)
    check(puzzle(readText("day_06/input_test_3"), 4) == 10)

    check(puzzle(readText("day_06/input_test_1"), 14) == 23)
    check(puzzle(readText("day_06/input_test_2"), 14) == 23)
    check(puzzle(readText("day_06/input_test_3"), 14) == 29)

    val input = readText("day_06/input")
    puzzle(input, 4).println()
    puzzle(input, 14).println()
}
