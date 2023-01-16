package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsListOfLong
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 20")
internal class Day20Test {
    private val exampleInput = resourceAsListOfLong("day20_example")
    private val problemInput = resourceAsListOfLong("day20")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(3L, Day20(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(4_426L, Day20(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(1_623_178_306L, Day20(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(8_119_137_886_612L, Day20(problemInput).puzzle2())
        }
    }
}
