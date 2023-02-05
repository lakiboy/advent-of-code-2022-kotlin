package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 23")
internal class Day23Test {
    private val exampleInput = resourceAsLines("day23_example")
    private val problemInput = resourceAsLines("day23")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(110, Day23(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(4_195, Day23(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(20, Day23(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(1_069, Day23(problemInput).puzzle2())
        }
    }
}
