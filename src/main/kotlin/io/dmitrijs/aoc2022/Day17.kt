package io.dmitrijs.aoc2022

class Day17(input: String) {
    private val moves = input.trim()

    fun puzzle1(): Int {
        val board = Board(7)
        var moveIndex = 0

        repeat(2022) { step ->
            val figure = figures[step % figures.size].also { board.place(it) }

            do {
                val leftOrRight = Direction.of(moves[moveIndex])
                moveIndex = ++moveIndex % moves.length
                board.move(figure, leftOrRight)
            } while (board.move(figure, Direction.DOWN))

            board.freeze(figure)
        }

        return board.figuresHeight
    }

    private class Board(private val width: Int, private val topBlankLines: Int = 3) {
        private val field = ArrayDeque<String>()
        private val blankLine = SPACE.toString().repeat(width)
        private var position = start

        val figuresHeight get() = field.size

        fun place(figure: Figure) {
            position = start
            field.insertBlankLines(topBlankLines + figure.height)
        }

        fun freeze(figure: Figure) {
            figure.placeAt(position)
            field.removeBlankLines()
        }

        fun move(figure: Figure, direction: Direction): Boolean {
            val next = position + direction

            return canMove(figure, next).also { success ->
                if (success) {
                    position = next
                }
            }
        }

        private fun canMove(figure: Figure, position: Point): Boolean {
            val (posX, posY) = position

            if (posX < 0 || (posX + figure.width) > width || (posY + figure.height) > field.size) {
                return false
            }

            return (0 until figure.height).all { y ->
                (0 until figure.width).all { x ->
                    field[posY + y][posX + x] == SPACE || figure[y][x] == SPACE
                }
            }
        }

        override fun toString() = field.joinToString("\n")

        private fun Figure.placeAt(position: Point) = repeat(height) { y ->
            val line = field[position.y + y]
            val replace = this[y].mapIndexed { x, char ->
                // This is the trick - take char from original line if figure has space.
                char.takeUnless { it == SPACE } ?: line[position.x + x]
            }.joinToString("")

            field[position.y + y] = line.substring(0, position.x) + replace + line.substring(position.x + width)
        }

        private fun ArrayDeque<String>.insertBlankLines(count: Int) = repeat(count) {
            addFirst(blankLine)
        }

        private fun ArrayDeque<String>.removeBlankLines() {
            do {
                val line = first()
                if (line == blankLine) {
                    removeFirst()
                }
            } while (line == blankLine)
        }
    }

    private data class Figure(private val lines: List<String>) {
        val width = lines.first().length
        val height = lines.size

        operator fun get(row: Int) = lines[row]

        companion object {
            fun of(vararg shape: String) = Figure(shape.toList())
        }
    }

    private fun Direction.Companion.of(char: Char) = when (char) {
        '>' -> Direction.RIGHT
        '<' -> Direction.LEFT
        else -> error("Invalid direction supplied: $char.")
    }

    companion object {
        private const val SPACE = '.'
        private val start = Point(2, 0)
        private val figures = listOf(
            Figure.of(
                "@@@@",
            ),
            Figure.of(
                ".@.",
                "@@@",
                ".@.",
            ),
            Figure.of(
                "..@",
                "..@",
                "@@@",
            ),
            Figure.of(
                "@",
                "@",
                "@",
                "@",
            ),
            Figure.of(
                "@@",
                "@@",
            ),
        )
    }
}
