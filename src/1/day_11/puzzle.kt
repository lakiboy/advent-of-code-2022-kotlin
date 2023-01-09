package day_11

import println
import readText

typealias Expression = (Long) -> Long

private fun expressionFactory(expr: String) = { value: Long ->
    val (a, op, b) = expr.replace("old", value.toString()).split(" ")
    when (op) {
        "*" -> a.toLong() * b.toLong()
        "+" -> a.toLong() + b.toLong()
        "-" -> a.toLong() - b.toLong()
        else -> error("Unknown expression operator.")
    }
}

data class Monkey(
    val id: Int,
    val items: MutableList<Long>,
    val trueId: Int,
    val falseId: Int,
    val expression: Expression,
    val divisor: Long,
)

class Game(private val monkeys: List<Monkey>, rounds: Int, relief: Long) {
    private val inspections = MutableList(monkeys.size) { 0 }

    private val maxLevel = monkeys.fold(1L) { acc, monkey -> acc * monkey.divisor }

    val monkeyBusiness
        get() = inspections
            .sortedDescending()
            .take(2)
            .zipWithNext { a, b -> a.toLong() * b }
            .single()

    private fun Long.moveTo(monkeyId: Int) = monkeys[monkeyId].items.add(this)

    init {
        val reduce: Expression = { value -> if (relief == 1L) value % maxLevel else value / relief }

        repeat(rounds) {
            monkeys.forEach { monkey ->
                with(monkey) {
                    items.forEach { level ->
                        val newLevel = reduce(expression(level))
                        val monkeyId = if (newLevel % divisor == 0L) trueId else falseId
                        newLevel.moveTo(monkeyId)
                    }
                    inspections[id] += items.size
                    items.clear()
                }
            }
        }
    }
}

fun puzzle(input: String, rounds: Int, relief: Int): Long {
    fun String.toExpression() = expressionFactory(substringAfter(" = "))

    fun String.toDivisor() = substringAfter(" by ").toLong()

    val monkeys = input.split("\n\n").mapIndexed { index, commands ->
        val (itemsCmd, operationCmd, testCmd, trueOutcomeCmd, falseOutcomeCmd) = commands
            .split("\n")
            .drop(1)
            .map { it.trim() }
        val items = itemsCmd
            .substringAfter(": ")
            .split(", ")
            .map { it.toLong() }
            .toMutableList()

        Monkey(
            id = index,
            items = items,
            trueId = trueOutcomeCmd.substringAfter("monkey ").toInt(),
            falseId = falseOutcomeCmd.substringAfter("monkey ").toInt(),
            expression = operationCmd.toExpression(),
            divisor = testCmd.toDivisor()
        )
    }

    return Game(monkeys, rounds, relief.toLong()).monkeyBusiness
}

fun main() {
    val testInput = readText("day_11/input_test")
    check(puzzle(testInput, 20, 3) == 10_605L)
    check(puzzle(testInput, 10_000, 1) == 2_713_310_158L)

    val input = readText("day_11/input")
    puzzle(input, 20, 3).println()
    puzzle(input, 10_000, 1).println()
}
