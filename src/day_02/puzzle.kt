package day_02

import println
import readInput

enum class Outcome(val points: Int) {
    WIN(6),
    DRAW(3),
    LOSS(0);

    companion object {
        fun fromChar(char: Char) = when (char) {
            'X' -> LOSS
            'Y' -> DRAW
            'Z' -> WIN
            else -> error("Unsupported character.")
        }
    }
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
            else -> error("Unsupported character.")
        }
    }
}

typealias Game = Pair<Shape, Shape>

typealias Prediction = Pair<Shape, Outcome>

private val Game.outcome
    get() = when (this) {
        Shape.ROCK to Shape.ROCK,
        Shape.PAPER to Shape.PAPER,
        Shape.SCISSORS to Shape.SCISSORS -> Outcome.DRAW

        Shape.ROCK to Shape.SCISSORS,
        Shape.PAPER to Shape.ROCK,
        Shape.SCISSORS to Shape.PAPER -> Outcome.LOSS

        else -> Outcome.WIN
    }

private val Game.myScore get() = second.points + outcome.points

private val Prediction.game
    get() = when (this) {
        Shape.ROCK to Outcome.WIN,
        Shape.PAPER to Outcome.DRAW,
        Shape.SCISSORS to Outcome.LOSS -> first to Shape.PAPER

        Shape.PAPER to Outcome.LOSS,
        Shape.ROCK to Outcome.DRAW,
        Shape.SCISSORS to Outcome.WIN -> first to Shape.ROCK

        else -> first to Shape.SCISSORS
    }

private fun createGame(moves: String) = Shape.fromChar(moves[0]) to Shape.fromChar(moves[2])

private fun createPrediction(moves: String) = Shape.fromChar(moves[0]) to Outcome.fromChar(moves[2])

fun puzzle1(input: List<String>) = input.map(::createGame).sumOf { it.myScore }

fun puzzle2(input: List<String>) = input.map(::createPrediction).sumOf { it.game.myScore }

fun main() {
    val testInput = readInput("day_02/input_test")
    check(puzzle1(testInput) == 15)
    check(puzzle2(testInput) == 12)

    val input = readInput("day_02/input")
    puzzle1(input).println()
    puzzle2(input).println()
}
