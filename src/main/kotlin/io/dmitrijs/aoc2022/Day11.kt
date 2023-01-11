package io.dmitrijs.aoc2022

class Day11(input: String) {
    private val monkeys = createMonkeys(input)

    fun puzzle1() = solve(20) { it / 3L }

    fun puzzle2(): Long {
        val maxLevel = monkeys.fold(1L) { acc, monkey -> acc * monkey.divisor }

        return solve(10_000) { it % maxLevel }
    }

    private fun solve(rounds: Int, reduce: (Long) -> Long): Long {
        val inspections = MutableList(monkeys.size) { 0 }
        val monkeyItems = monkeys.map { it.startingItems.toMutableList() }

        repeat(rounds) {
            monkeys.forEach { monkey ->
                with(monkey) {
                    val currentItems = monkeyItems[id]
                    currentItems.forEach { level ->
                        val newLevel = reduce(expression(level))
                        val monkeyId = if (newLevel % divisor == 0L) trueId else falseId
                        monkeyItems[monkeyId].add(newLevel)
                    }
                    inspections[id] += currentItems.size
                    currentItems.clear()
                }
            }
        }

        return inspections
            .sortedDescending()
            .take(2)
            .zipWithNext { a, b -> a.toLong() * b }
            .single()
    }

    private fun String.toExpression() = expressionFactory(substringAfter(" = "))

    private fun String.toDivisor() = substringAfter(" by ").toLong()

    private data class Monkey(
        val id: Int,
        val startingItems: List<Long>,
        val trueId: Int,
        val falseId: Int,
        val expression: (Long) -> Long,
        val divisor: Long,
    )

    private fun expressionFactory(expr: String) = { value: Long ->
        val (a, op, b) = expr.replace("old", value.toString()).split(" ")
        when (op) {
            "*" -> a.toLong() * b.toLong()
            "+" -> a.toLong() + b.toLong()
            "-" -> a.toLong() - b.toLong()
            else -> error("Unknown expression operator.")
        }
    }

    private fun createMonkeys(input: String) = input.split("\n\n").mapIndexed { index, commands ->
        val (itemsCmd, operationCmd, testCmd, trueOutcomeCmd, falseOutcomeCmd) = commands
            .split("\n")
            .drop(1)
            .map { it.trim() }
        val items = itemsCmd
            .substringAfter(": ")
            .split(", ")
            .map { it.toLong() }

        Monkey(
            id = index,
            startingItems = items,
            trueId = trueOutcomeCmd.substringAfter("monkey ").toInt(),
            falseId = falseOutcomeCmd.substringAfter("monkey ").toInt(),
            expression = operationCmd.toExpression(),
            divisor = testCmd.toDivisor()
        )
    }
}
