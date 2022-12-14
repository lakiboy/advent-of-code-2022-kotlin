private val priorityMap: Map<Char, Int> = buildMap {
    ('a'..'z').mapIndexed { index, char ->
        set(char, index + 1)
        set(char.uppercaseChar(), index + 27)
    }
}

private val Char.priority get() = priorityMap[this] ?: 0

private fun String.intersect(other: String) = toCharArray().toSet().intersect(other.toCharArray().toSet())

private fun findUniqueElement(line: String): Char {
    val pos = line.length / 2
    val one = line.substring(0, pos)
    val two = line.substring(pos)

    return one.intersect(two).first()
}

private fun findBadgeElement(lines: List<String>): Char {
    val one = lines[0].intersect(lines[1])
    val two = lines[1].intersect(lines[2])

    return one.intersect(two).first()
}

private fun puzzle1(input: List<String>) = input.sumOf { findUniqueElement(it).priority }

private fun puzzle2(input: List<String>) = input.chunked(3).sumOf { findBadgeElement(it).priority }

fun main() {
    val testInput = readInput("day_03_input_test")
    check(puzzle1(testInput) == 157)
    check(puzzle2(testInput) == 70)

    val input = readInput("day_03_input")
    puzzle1(input).println()
    puzzle2(input).println()
}
