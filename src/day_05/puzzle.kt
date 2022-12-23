package day_05

import println
import readText

typealias CargoStack = ArrayDeque<Char>

typealias CargoCommand = Triple<Int, Int, Int>

fun List<CargoStack>.top() = joinToString("") { it.first().toString() }

fun List<CargoStack>.move(command: CargoCommand) {
    val (count, from, to) = command
    repeat(count) { this[to - 1].addFirst(this[from - 1].removeFirst()) }
}

fun List<CargoStack>.moveAtOnce(command: CargoCommand) {
    val (count, from, to) = command
    repeat(count) { this[to - 1].addFirst(this[from - 1].removeAt(count - it - 1)) }
}

fun readStacks(input: String, num: Int): List<CargoStack> {
    val stacks = List(num) { CargoStack() }

    input.lines().dropLast(1).forEach { line ->
        repeat(num) { col ->
            line.getOrNull(col * 4 + 1)
                .takeUnless { it == ' ' }
                ?.let { stacks[col].add(it) }
        }
    }

    return stacks
}

fun readCommand(input: String): CargoCommand {
    val (count, _, from, _, to) = input.substring(5).split(" ")

    return CargoCommand(count.toInt(), from.toInt(), to.toInt())
}

fun executeSheet(sheet: String, cols: Int, operation: List<CargoStack>.(CargoCommand) -> Unit): String {
    val (stacksSheet, commandsSheet) = sheet.split("\n\n")

    val stacks = readStacks(stacksSheet, cols)
    val commands = commandsSheet.trim().lines()

    commands.forEach { stacks.operation(readCommand(it)) }

    return stacks.top()
}

fun puzzle1(sheet: String, cols: Int) = executeSheet(sheet, cols) { move(it) }

fun puzzle2(sheet: String, cols: Int) = executeSheet(sheet, cols) { moveAtOnce(it) }

fun main() {
    val testInput = readText("day_05/input_test")
    check(puzzle1(testInput, 3) == "CMZ")
    check(puzzle2(testInput, 3) == "MCD")

    val input = readText("day_05/input")
    puzzle1(input, 9).println()
    puzzle2(input, 9).println()
}
