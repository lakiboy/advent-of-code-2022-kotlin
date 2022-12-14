fun main() {
    fun reduceLines(lines: List<String>): List<Int> {
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

    fun puzzle1(input: List<String>) = reduceLines(input).max()

    fun puzzle2(input: List<String>) = reduceLines(input)
        .sortedDescending()
        .take(3)
        .sum()

    val testInput = readInput("day_01_input_test")
    check(puzzle1(testInput) == 24000)

    val input = readInput("day_01_input")
    puzzle1(input).println()
    puzzle2(input).println()
}
