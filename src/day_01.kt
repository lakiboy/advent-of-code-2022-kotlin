fun main() {
    fun reduceItems(items: List<String>): List<Int> {
        var index = 0

        return items.fold(mutableListOf(0)) { list, line ->
            if (line.isBlank()) {
                list.add(++index, 0)
            } else {
                list[index] += line.toInt()
            }

            list
        }
    }

    fun part1(input: List<String>) = reduceItems(input).max()

    fun part2(input: List<String>) = reduceItems(input)
        .sortedDescending()
        .take(3)
        .sum()

    val testInput = readInput("day_01_input_test")
    check(part1(testInput) == 24000)

    val input = readInput("day_01_input")
    part1(input).println()
    part2(input).println()
}
