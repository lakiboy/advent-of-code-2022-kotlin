enum class Outcome(val points: Int) {
    WIN(6),
    DRAW(3),
    LOSS(0)
}

enum class Shape(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun fromChar(char: Char) = when (char) {
            'A', 'X' -> ROCK
            'B', 'Y' -> PAPER
            'C', 'Z' -> SCISSORS
            else -> error("Unsupported character")
        }
    }
}

typealias Game = Pair<Shape, Shape>

val Game.outcome get() = when (this) {
    Shape.ROCK to Shape.ROCK,
    Shape.PAPER to Shape.PAPER,
    Shape.SCISSORS to Shape.SCISSORS -> Outcome.DRAW
    Shape.ROCK to Shape.SCISSORS,
    Shape.PAPER to Shape.ROCK,
    Shape.SCISSORS to Shape.PAPER -> Outcome.LOSS
    else -> Outcome.WIN
}

val Game.myScore get() = second.points + outcome.points

fun main() {
    fun createGame(moves: String) = Shape.fromChar(moves[0]) to Shape.fromChar(moves[2])

    fun puzzle1(input: List<String>) = input.map(::createGame).sumOf { it.myScore }

    val testInput = readInput("day_02_input_test")
    check(puzzle1(testInput) == 15)

    val input = readInput("day_02_input")
    puzzle1(input).println()
}
