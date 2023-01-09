package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 1")
internal class Day01Test {
    private val exampleInput = resourceAsLines("day01_example")
    private val problemInput = resourceAsLines("day01")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(24_000, Day01(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(69_206, Day01(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(45_000, Day01(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(197_400, Day01(problemInput).puzzle2())
        }
    }
}
