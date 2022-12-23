package day_10

import println
import readInput

class Program(commands: List<String>) {
    private var x = 1
    private var cycles = 0
    private var cursor = 0

    // Read entire program into stack.
    private val stack = ArrayDeque<String>().apply {
        commands.forEach { cmd ->
            cmd.split(" ").forEach { add(it) }
        }
    }

    private val sprite get() = (x - 1)..(x + 1)

    var signalStrength = 0
    var screen = ""

    fun execute() {
        while (stack.isNotEmpty()) {
            screen += if (cursor in sprite) "#" else "."

            cycles++
            cursor++

            // Puzzle 1.
            if (cycles % 20 == 0 && cycles % 40 != 0) {
                signalStrength += cycles * x
            }

            // Puzzle 2
            if (cycles % 40 == 0) {
                screen += "\n"
                cursor = 0
            }

            when (val op = stack.removeFirst()) {
                "noop", "addx" -> Unit
                else -> x += op.toInt()
            }
        }

        screen = screen.trim()
    }
}

fun puzzle1(lines: List<String>) = Program(lines).apply { execute() }.signalStrength

fun puzzle2(lines: List<String>) = Program(lines).apply { execute() }.screen

fun main() {
    val output = """
        ##..##..##..##..##..##..##..##..##..##..
        ###...###...###...###...###...###...###.
        ####....####....####....####....####....
        #####.....#####.....#####.....#####.....
        ######......######......######......####
        #######.......#######.......#######.....
    """.trimIndent()

    val testInput = readInput("day_10/input_test")
    check(puzzle1(testInput) == 13140)
    check(puzzle2(testInput) == output)

    val input = readInput("day_10/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
