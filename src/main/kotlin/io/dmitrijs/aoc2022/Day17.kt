package io.dmitrijs.aoc2022

class Day17(input: String) {
    private val moves = input.trim()

    fun puzzle1() =
        Board(7).apply {
            var moveIndex = 0
            repeat(2022) { figureIndex ->
                moveIndex += takeTurn(figures[figureIndex % figures.size], moveIndex)
            }
        }.figuresHeight

    fun puzzle2(): Long {
        val board = Board(7)
        var turn = 0
        val figuresCount = 1_000_000_000_000L
        var figuredLeft = figuresCount
        var moveIndex = 0
        var patternHeight = 0L
        val patterns = hashMapOf<FigureRecord, MutableList<BoardState>>()

        fun FigureRecord.capturePattern() = this in patterns && patterns.getValue(this).size >= PATTERN_SIZE

        do {
            val boardState = BoardState(board.figuresHeight, turn)
            val figureIndex = turn++ % figures.size
            val record = FigureRecord(figureIndex, moveIndex)

            patterns.computeIfAbsent(record) { mutableListOf() }.add(boardState)

            if (record.capturePattern()) {
                val pattern = patterns.getValue(record)
                pattern
                    .zipWithNext { a, b -> b - a }
                    .reversed()
                    .zipWithNext()
                    .dropWhile { (a, b) -> a == b }
                    .size
                    .takeIf { pattern.size - it >= PATTERN_SIZE }
                    ?.let { patternStart ->
                        val start = pattern[patternStart]
                        val cycle = (pattern[patternStart + 1] - start)
                        val cyclesCount = (figuresCount - start.figures) / cycle.figures

                        patternHeight = start.height + cyclesCount * cycle.height - board.figuresHeight
                        figuredLeft = (figuresCount - start.figures) % cycle.figures

                        patterns.clear()
                    }
            }

            if (figuredLeft > 0) {
                moveIndex += board.takeTurn(figures[figureIndex], moveIndex)
                moveIndex %= moves.length
            }
        } while (--figuredLeft > 0)

        return patternHeight + board.figuresHeight
    }

    private fun Board.takeTurn(figure: Figure, moveIndex: Int): Int {
        var height = 0

        place(figure)

        do {
            val nextMove = (moveIndex + height++) % moves.length
            move(figure, Direction.of(moves[nextMove]))
        } while (move(figure, Direction.DOWN))

        freeze(figure)

        return height
    }

    private data class BoardState(val height: Int, val figures: Int) {
        operator fun minus(other: BoardState) = copy(
            height = height - other.height,
            figures = figures - other.figures,
        )
    }

    private data class FigureRecord(val figureIndex: Int, val moveIndex: Int)

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
        private const val PATTERN_SIZE = 3
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
