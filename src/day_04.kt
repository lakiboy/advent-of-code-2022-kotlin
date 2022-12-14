private typealias Section = Pair<Int, Int>

private typealias CompareFn = (Section, Section) -> Boolean

private fun Section.contains(number: Int) = number in first..second

private fun Section.contains(other: Section) = first <= other.first && second >= other.second

private fun Section.intersects(other: Section) = other.contains(first) || other.contains(second)

private fun List<Int>.toPair() = this[0] to this[1]

private fun compare(line: String, fn: CompareFn): Boolean {
    val (one, two) = line
        .split(",")
        .map { part -> part.split("-").map(String::toInt) }
        .map { list -> list.toPair() }

    return fn(one, two)
}

private fun within(one: Section, two: Section) = one.contains(two) || two.contains(one)

private fun overlap(one: Section, two: Section) = one.intersects(two) || two.intersects(one)

private fun puzzle1(input: List<String>) = input.count { line -> compare(line, ::within) }

private fun puzzle2(input: List<String>) = input.count { line -> compare(line, ::overlap) }

fun main() {
    val testInput = readInput("day_04_input_test")
    check(puzzle1(testInput) == 2)
    check(puzzle2(testInput) == 4)

    val input = readInput("day_04_input")
    puzzle1(input).println()
    puzzle2(input).println()
}