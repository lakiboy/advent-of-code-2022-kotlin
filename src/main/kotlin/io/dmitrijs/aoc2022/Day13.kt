package io.dmitrijs.aoc2022

class Day13(private val input: String) {
    fun puzzle1() = input
        .split("\n\n")
        .map { it.toPairs() }
        .foldIndexed(0) { index, acc, (left, right) ->
            (if (left < right) (index + 1) else 0) + acc
        }

    fun puzzle2(): Int {
        val dividers: Pair<Packet, Packet>

        return "[[2]]\n[[6]]\n\n$input"
            .lines()
            .filter { it.isNotEmpty() }
            .map { Packet.of(it) }
            .also { packets -> dividers = packets[0] to packets[1] }
            .sorted()
            .let { (it.indexOf(dividers.first) + 1) * (it.indexOf(dividers.second) + 1) }
    }

    private fun String.toPairs() = Pair(
        Packet.of(substringBefore("\n").trim()),
        Packet.of(substringAfter("\n").trim()),
    )

    private sealed class Packet : Comparable<Packet> {
        companion object {
            fun of(line: String) = if ('[' in line) Items.fromString(line) else Number.fromString(line)
        }

        class Number(private val value: Int) : Packet() {
            override operator fun compareTo(other: Packet) = when (other) {
                is Number -> value - other.value
                is Items -> asItems().compareTo(other)
            }

            fun asItems() = Items(listOf(this))

            companion object {
                fun fromString(s: String) = Number(s.toInt())
            }
        }

        class Items(private val items: List<Packet>) : Packet() {
            override operator fun compareTo(other: Packet): Int = when (other) {
                is Number -> compareTo(other.asItems())
                is Items -> items.zip(other.items)
                    .firstNotNullOfOrNull { (left, right) -> left.compareTo(right).takeUnless { it == 0 } }
                    ?: (items.size - other.items.size)
            }

            companion object {
                fun fromString(s: String): Items {
                    val stack = ArrayDeque<Char>()
                    var level = 0
                    val items = mutableListOf<Packet>()

                    s.removePrefix("[").removeSuffix("]").forEach { char ->
                        when (char) {
                            '[' -> level++
                            ']' -> level--
                        }

                        stack.add(char)

                        if (char == ',' && level == 0) {
                            stack.removeLast()
                            items.add(of(stack.joinToString("")))
                            stack.clear()
                        }
                    }

                    if (stack.isNotEmpty()) {
                        items.add(of(stack.joinToString("")))
                    }

                    return Items(items)
                }
            }
        }
    }
}
