package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 14")
internal class Day14Test {
    private val exampleInput = resourceAsLines("day14_example")
    private val problemInput = resourceAsLines("day14")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(24, Day14(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(779, Day14(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(93, Day14(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(27_426, Day14(problemInput).puzzle2())
        }
    }
}
