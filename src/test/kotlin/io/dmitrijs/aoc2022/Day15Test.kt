package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 15")
internal class Day15Test {
    private val exampleInput = resourceAsLines("day15_example")
    private val problemInput = resourceAsLines("day15")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(26, Day15(exampleInput).puzzle1(10))
        }

        @Test
        fun `solves problem`() {
            assertEquals(4_873_353, Day15(problemInput).puzzle1(2_000_000))
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(56_000_011L, Day15(exampleInput).puzzle2(20))
        }

        @Test
        fun `solves problem`() {
            assertEquals(11_600_823_139_120L, Day15(problemInput).puzzle2(4_000_000))
        }
    }
}
