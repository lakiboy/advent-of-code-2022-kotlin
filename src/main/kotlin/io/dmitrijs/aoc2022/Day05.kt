package io.dmitrijs.aoc2022

typealias CargoStack = ArrayDeque<Char>

typealias CargoCommand = Triple<Int, Int, Int>

class Day05(private val input: String, private val cols: Int) {
    fun puzzle1() = executeSheet { move(it) }.top()

    fun puzzle2() = executeSheet { moveAtOnce(it) }.top()

    private fun List<CargoStack>.top() = joinToString("") { it.first().toString() }

    private fun String.toCommand(): CargoCommand {
        val (count, _, from, _, to) = substring(5).split(" ")

        return CargoCommand(count.toInt(), from.toInt(), to.toInt())
    }

    private fun List<CargoStack>.move(command: CargoCommand) {
        val (count, from, to) = command
        repeat(count) { this[to - 1].addFirst(this[from - 1].removeFirst()) }
    }

    private fun List<CargoStack>.moveAtOnce(command: CargoCommand) {
        val (count, from, to) = command
        repeat(count) { this[to - 1].addFirst(this[from - 1].removeAt(count - it - 1)) }
    }

    private fun String.toStacks() =
        List(cols) { CargoStack() }.apply {
            lines().dropLast(1).forEach { line ->
                repeat(cols) { col ->
                    line.getOrNull(col * 4 + 1).takeUnless { it == ' ' }?.let { this[col].add(it) }
                }
            }
        }

    private fun executeSheet(operation: List<CargoStack>.(CargoCommand) -> Unit): List<CargoStack> {
        val (stacksSheet, commandsSheet) = input.split("\n\n")

        return stacksSheet.toStacks().apply {
            commandsSheet
                .trim()
                .lines()
                .forEach { this.operation(it.toCommand()) }
        }
    }
}
