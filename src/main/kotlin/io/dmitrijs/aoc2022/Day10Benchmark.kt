package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup
import org.openjdk.jmh.annotations.Fork

@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 4)
@Fork(1)
class Day10Benchmark {
    private lateinit var input: List<String>

    @Setup
    fun setUp() {
        input = resourceAsLines("day10")
    }

    @Benchmark
    fun puzzle1() = Day10(input).puzzle1()

    @Benchmark
    fun puzzle2() = Day10(input).puzzle2()
}
