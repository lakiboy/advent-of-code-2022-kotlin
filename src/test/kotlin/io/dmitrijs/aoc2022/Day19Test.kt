package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 19")
internal class Day19Test {
    private val exampleInput = resourceAsLines("day19_example")
    private val problemInput = resourceAsLines("day19")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(33, Day19(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(4_364, Day19(problemInput).puzzle1())
        }
    }
}
