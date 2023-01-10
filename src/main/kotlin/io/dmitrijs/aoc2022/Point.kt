package io.dmitrijs.aoc2022

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = copy(x = x + other.x, y = y + other.y)

    operator fun times(distance: Int) = copy(x = x * distance, y = y * distance)
}

enum class Direction(val move: Point) {
    UP(move = Point(0, -1)),
    DOWN(move = Point(0, 1)),
    LEFT(move = Point(-1, 0)),
    RIGHT(move = Point(1, 0));

    companion object
}
