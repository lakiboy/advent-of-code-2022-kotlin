package io.dmitrijs.aoc2022

import kotlin.math.pow

class Day25(private val input: List<String>) {
    private val snafuToDec = mapOf('0' to 0L, '1' to 1L, '2' to 2L, '-' to -1L, '=' to -2L)
    private val decToSnafu = snafuToDec.map { (k, v) -> v to k }.toMap()

    fun puzzle1() = input.fold(0L) { acc, expr -> acc + expr.toDec() }.toSnafu()

    private fun Long.toSnafu() = buildList {
        var num = this@toSnafu
        do {
            val rem = num % 5
            num /= 5
            if (rem > 2) {
                num += 1
                add(decToSnafu.getValue(rem - 5))
            } else {
                add(decToSnafu.getValue(rem))
            }
        } while (num > 0)
    }.reversed().joinToString("")

    private fun String.toDec() = indices.fold(0L) { acc, power ->
        acc + snafuToDec.getValue(this[length - power - 1]) * 5.0.pow(power).toLong()
    }
}
