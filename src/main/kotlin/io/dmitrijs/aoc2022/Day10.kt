package io.dmitrijs.aoc2022

class Day10(input: List<String>) {
    private val program by lazy {
        Program(input).apply { execute() }
    }

    fun puzzle1() = program.signalStrength

    fun puzzle2() = program.screen

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
            private set
        var screen = ""
            private set

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
}
