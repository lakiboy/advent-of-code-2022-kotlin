package day_04

import println
import readInput

typealias Section = Pair<Int, Int>

typealias CompareFn = (Section, Section) -> Boolean

fun Section.contains(number: Int) = number in first..second

fun Section.contains(other: Section) = first <= other.first && second >= other.second

fun Section.intersects(other: Section) = other.contains(first) || other.contains(second)

fun List<Int>.toPair() = this[0] to this[1]

fun compare(line: String, fn: CompareFn): Boolean {
    val (one, two) = line
        .split(",")
        .map { it.split("-").map(String::toInt).toPair() }

    return fn(one, two)
}

fun within(one: Section, two: Section) = one.contains(two) || two.contains(one)

fun overlap(one: Section, two: Section) = one.intersects(two) || two.intersects(one)

fun puzzle1(input: List<String>) = input.count { line -> compare(line, ::within) }

fun puzzle2(input: List<String>) = input.count { line -> compare(line, ::overlap) }

fun main() {
    val testInput = readInput("day_04/input_test")
    check(puzzle1(testInput) == 2)
    check(puzzle2(testInput) == 4)

    val input = readInput("day_04/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
