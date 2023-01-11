package io.dmitrijs.aoc2022

class Day02(private val input: List<String>) {
    fun puzzle1() = input.map(::createGame).sumOf { it.myScore }

    fun puzzle2() = input.map(::createPrediction).sumOf { it.game.myScore }

    private fun createGame(moves: String) = Shape.of(moves[0]) to Shape.of(moves[2])

    private fun createPrediction(moves: String) = Shape.of(moves[0]) to Outcome.of(moves[2])

    private enum class Outcome(val points: Int) {
        WIN(6),
        DRAW(3),
        LOSS(0);

        companion object {
            fun of(char: Char) = when (char) {
                'X' -> LOSS
                'Y' -> DRAW
                'Z' -> WIN
                else -> error("Unsupported character.")
            }
        }
    }

    private enum class Shape(val points: Int) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        companion object {
            fun of(char: Char) = when (char) {
                'A', 'X' -> ROCK
                'B', 'Y' -> PAPER
                'C', 'Z' -> SCISSORS
                else -> error("Unsupported character.")
            }
        }
    }

    private val Pair<Shape, Shape>.myScore get() = second.points + outcome.points

    private val Pair<Shape, Shape>.outcome
        get() = when (this) {
            Shape.ROCK to Shape.ROCK,
            Shape.PAPER to Shape.PAPER,
            Shape.SCISSORS to Shape.SCISSORS -> Outcome.DRAW

            Shape.ROCK to Shape.SCISSORS,
            Shape.PAPER to Shape.ROCK,
            Shape.SCISSORS to Shape.PAPER -> Outcome.LOSS

            else -> Outcome.WIN
        }

    private val Pair<Shape, Outcome>.game
        get() = when (this) {
            Shape.ROCK to Outcome.WIN,
            Shape.PAPER to Outcome.DRAW,
            Shape.SCISSORS to Outcome.LOSS -> first to Shape.PAPER

            Shape.PAPER to Outcome.LOSS,
            Shape.ROCK to Outcome.DRAW,
            Shape.SCISSORS to Outcome.WIN -> first to Shape.ROCK

            else -> first to Shape.SCISSORS
        }
}
