package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 8")
internal class Day08Test {
    private val exampleInput = resourceAsLines("day08_example")
    private val problemInput = resourceAsLines("day08")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(21, Day08(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(1_823, Day08(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(8, Day08(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(211_680, Day08(problemInput).puzzle2())
        }
    }
}
