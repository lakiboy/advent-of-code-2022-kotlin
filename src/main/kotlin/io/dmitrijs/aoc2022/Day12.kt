package io.dmitrijs.aoc2022

import java.util.PriorityQueue

class Day12(input: List<String>) {
    private lateinit var start: Point
    private lateinit var finish: Point
    private val grid = input.mapIndexed { y, line ->
        line.mapIndexed { x, char ->
            when (char) {
                'S' -> {
                    start = Point(x, y)
                    START
                }
                'E' -> {
                    finish = Point(x, y)
                    FINISH
                }
                else -> char - 'a' + 1
            }
        }
    }

    fun puzzle1() = bfs(start, incDec = 1) { it == finish }

    fun puzzle2() = bfs(finish, incDec = -1) { it.value == START }

    fun puzzle1Dijkstra() = dijkstra(start, incDec = 1) { it == finish }

    fun puzzle2Dijkstra() = dijkstra(finish, incDec = -1) { it.value == START }

    private val Point.valid get() = x >= 0 && y >= 0 && y <= grid.lastIndex && x <= grid[y].lastIndex

    private val Point.value get() = grid[y][x]

    private fun Point.neighbours(incDec: Int): List<Point> {
        return Direction
            .values()
            .map { this + it }
            .filter { to -> to.valid }
            .filter { to -> if (incDec > 0) value + incDec >= to.value else value + incDec <= to.value }
    }

    private fun bfs(root: Point, incDec: Int, done: (Point) -> Boolean): Int {
        val queue = ArrayDeque<Pair<Point, Int>>()
        val visited = hashSetOf<Point>()

        queue.add(root to 0)

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.removeFirst()

            if (done(node)) {
                return distance
            }

            node.neighbours(incDec).filter { it !in visited }.forEach { neighbour ->
                queue.add(neighbour to (distance + 1))
                visited.add(neighbour)
            }
        }

        return -1
    }

    private fun dijkstra(root: Point, incDec: Int, done: (Point) -> Boolean): Int {
        val queue = PriorityQueue<Visit>()
        val cost = hashMapOf<Point, Int>()

        queue.add(Visit(root, 0))
        cost[root] = 0

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.poll()

            if (done(node)) {
                return distance
            }

            val newCost = cost.getValue(node) + 1

            node.neighbours(incDec).forEach { neighbour ->
                if (neighbour !in cost || newCost < cost.getValue(neighbour)) {
                    cost[neighbour] = newCost
                    queue.add(Visit(neighbour, newCost))
                }
            }
        }

        return -1
    }

    /*
    private fun greedyBfs(root: Point): Int {
        val queue = PriorityQueue<Visit>()
        val visited = mutableSetOf<Point>()

        queue.add(Visit(root, 0, root.distanceTo(finish)))

        while (queue.isNotEmpty()) {
            val (node, distance) = queue.poll()

            if (node == finish) {
                return distance
            }

            node.neighbours(incDec = 1).filter { it !in visited }.forEach { neighbour ->
                queue.add(Visit(neighbour, distance + 1, neighbour.distanceTo(finish)))
                visited.add(neighbour)
            }
        }

        return -1
    }
    */

    private data class Visit(val p: Point, val d: Int) : Comparable<Visit> {
        override fun compareTo(other: Visit) = d.compareTo(other.d)
    }

    companion object {
        private const val START = 1
        private const val FINISH = 26
    }
}
