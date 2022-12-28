package day_13

import println
import readText

sealed class Packet : Comparable<Packet> {
    companion object {
        fun fromLine(line: String) = if ('[' in line) Items.fromString(line) else Number.fromString(line)
    }

    override operator fun compareTo(other: Packet): Int = when (this) {
        is Number -> when (other) {
            is Number -> compareTo(other)
            is Items -> asItems().compareTo(other)
        }
        is Items -> when (other) {
            is Number -> compareTo(other.asItems())
            is Items -> zip(other).firstNotNullOfOrNull { (left, right) ->
                left.compareTo(right).takeUnless { it == 0 }
            } ?: (size - other.size)
        }
    }

    data class Number(private val value: Int) : Packet() {
        operator fun compareTo(other: Number) = value - other.value

        fun asItems() = Items(listOf(this))

        companion object {
            fun fromString(s: String) = Number(s.toInt())
        }
    }

    data class Items(private val items: List<Packet>) : Packet() {
        val size get() = items.size

        fun zip(other: Items) = items.zip(other.items)

        companion object {
            fun fromString(s: String): Items {
                val stack = mutableListOf<Char>()
                var level = 0
                val items = mutableListOf<Packet>()

                s.removePrefix("[").removeSuffix("]").forEach { char ->
                    when (char) {
                        '[' -> level++
                        ']' -> level--
                    }

                    stack.add(char)

                    if (char == ',' && level == 0) {
                        items.add(fromLine(stack.dropLast(1).joinToString("")))
                        stack.clear()
                    }
                }

                if (stack.isNotEmpty()) {
                    items.add(fromLine(stack.joinToString("")))
                }

                return Items(items)
            }
        }
    }
}

private fun String.toPairs() = Pair(
    Packet.fromLine(substringBefore("\n").trim()),
    Packet.fromLine(substringAfter("\n").trim()),
)

fun puzzle1(input: String) = input
    .split("\n\n")
    .map { it.toPairs() }
    .foldIndexed(0) { index, acc, (left, right) ->
        (if (left < right) (index + 1) else 0) + acc
    }

fun puzzle2(input: String): Int {
    val dividers: Pair<Packet, Packet>

    return "[[2]]\n[[6]]\n\n$input"
        .lines()
        .filter { it.isNotEmpty() }
        .map { Packet.fromLine(it) }
        .also { packets -> dividers = packets[0] to packets[1] }
        .sorted()
        .let { (it.indexOf(dividers.first) + 1) * (it.indexOf(dividers.second) + 1) }
}

fun main() {
    val testInput = readText("day_13/input_test")
    check(puzzle1(testInput) == 13)
    check(puzzle2(testInput) == 140)

    val input = readText("day_13/input")
    puzzle1(input).println()
    puzzle2(input).println()
}