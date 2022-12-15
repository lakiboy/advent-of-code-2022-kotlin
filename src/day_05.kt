typealias CargoStack = ArrayDeque<Char>

typealias CargoCommand = Triple<Int, Int, Int>

private fun List<CargoStack>.top() = joinToString("") { it.first().toString() }

private fun List<CargoStack>.move(command: CargoCommand) {
    val (count, from, to) = command
    repeat(count) { this[to - 1].addFirst(this[from - 1].removeFirst()) }
}

private fun List<CargoStack>.moveAtOnce(command: CargoCommand) {
    val (count, from, to) = command
    repeat(count) { this[to - 1].addFirst(this[from - 1].removeAt(count - it - 1)) }
}

private fun readStacks(input: String, num: Int): List<CargoStack> {
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

private fun readMove(input: String): CargoCommand {
    val (count, _, from, _, to) = input.substring(5).split(" ")

    return CargoCommand(count.toInt(), from.toInt(), to.toInt())
}

private fun puzzle1(input: String, cols: Int): String {
    val (stacksSheet, movesSheet) = input.split("\n\n")

    val stacks = readStacks(stacksSheet, cols)
    val moves = movesSheet.trim().lines()

    moves.forEach { stacks.move(readMove(it)) }

    return stacks.top()
}

private fun puzzle2(input: String, cols: Int): String {
    val (stacksSheet, movesSheet) = input.split("\n\n")

    val stacks = readStacks(stacksSheet, cols)
    val moves = movesSheet.trim().lines()

    moves.forEach { stacks.moveAtOnce(readMove(it)) }

    return stacks.top()
}

fun main() {
    val testInput = readText("day_05_input_test")
    check(puzzle1(testInput, 3) == "CMZ")
    check(puzzle2(testInput, 3) == "MCD")

    val input = readText("day_05_input")
    puzzle1(input, 9).println()
    puzzle2(input, 9).println()
}
