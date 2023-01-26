package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 21")
internal class Day21Test {
    private val exampleInput = resourceAsLines("day21_example")
    private val problemInput = resourceAsLines("day21")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(152L, Day21(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(87_457_751_482_938L, Day21(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(301L, Day21(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(3_221_245_824_363L, Day21(problemInput).puzzle2())
        }
    }
}
