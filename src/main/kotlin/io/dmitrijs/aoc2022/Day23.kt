package io.dmitrijs.aoc2022

class Day23(input: List<String>) {
    private val elves = input.flatMapIndexed { y, line ->
        line.indices.filter { x -> line[x] == ELF }.map { x -> Point(x, y) }
    }

    fun puzzle1() = Grid(elves).apply {
        repeat(10) { move() }
    }.emptyTiles

    fun puzzle2(): Int {
        val grid = Grid(elves)
        var round = 1

        while (grid.move() > 0) {
            round++
        }

        return round
    }

    private class Grid(input: List<Point>) {
        private val elves = input.toMutableSet()
        private var sides = Direction.values().toList()

        val emptyTiles get(): Int {
            val minX = elves.minOf { it.x }
            val maxX = elves.maxOf { it.x }
            val minY = elves.minOf { it.y }
            val maxY = elves.maxOf { it.y }

            return (maxY - minY + 1) * (maxX - minX + 1) - elves.size
        }

        fun move(): Int {
            val moving = elves.filter { elf ->
                elf.neighbours().any { it in elves }
            }.mapNotNull { elf ->
                elf.moveTowards()?.let { destination -> elf to destination }
            }.groupBy({ (_, destination) -> destination }) { (elf, _) ->
                elf
            }.filterValues { elves ->
                elves.size == 1
            }.mapValues { (_, elves) ->
                elves.single()
            }

            val old = moving.values.toSet()
            val new = moving.keys

            elves.apply {
                removeAll(old)
                addAll(new)
            }
            sides = sides.drop(1) + sides.first()

            // Alternatively can be immutable, but mutable structure is faster.
            // elves = elves - old + new

            return new.size
        }

        private fun Point.moveTowards() =
            sides.firstOrNull { side ->
                side.positions.all { delta -> (this + delta) !in elves }
            }?.let { side -> this + side.positions.first() }
    }

    private enum class Direction(val positions: List<Point>) {
        N(listOf(Point(0, -1), Point(-1, -1), Point(1, -1))),
        S(listOf(Point(0, 1), Point(-1, 1), Point(1, 1))),
        W(listOf(Point(-1, 0), Point(-1, -1), Point(-1, 1))),
        E(listOf(Point(1, 0), Point(1, -1), Point(1, 1)));
    }

    companion object {
        const val ELF = '#'
    }
}
