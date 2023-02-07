package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 24")
internal class Day24Test {
    private val exampleInput = resourceAsLines("day24_example")
    private val problemInput = resourceAsLines("day24")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(18, Day24(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(437, Day24(problemInput).puzzle1())
        }
    }
}
