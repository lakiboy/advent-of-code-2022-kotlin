package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 18")
internal class Day18Test {
    private val exampleInput = resourceAsLines("day18_example")
    private val problemInput = resourceAsLines("day18")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(64, Day18(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(4_364, Day18(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(58, Day18(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(2_508, Day18(problemInput).puzzle2())
        }
    }
}
