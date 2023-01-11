package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 13")
internal class Day13Test {
    private val exampleInput = resourceAsText("day13_example")
    private val problemInput = resourceAsText("day13")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(13, Day13(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(6_101, Day13(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(140, Day13(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(21_909, Day13(problemInput).puzzle2())
        }
    }
}
