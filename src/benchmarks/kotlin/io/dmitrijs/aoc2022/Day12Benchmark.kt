package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State

@State(Scope.Benchmark)
internal class Day12Benchmark {
    private lateinit var input: List<String>

    @Setup
    fun setUp() {
        input = resourceAsLines("day12")
    }

    @Benchmark
    fun puzzle1Bfs() = Day12(input).puzzle1()

    @Benchmark
    fun puzzle2Bfs() = Day12(input).puzzle2()

    @Benchmark
    fun puzzle1Dijkstra() = Day12(input).puzzle1Dijkstra()

    @Benchmark
    fun puzzle2Dijkstra() = Day12(input).puzzle2Dijkstra()
}
