package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 5")
internal class Day05Test {
    private val exampleInput = resourceAsText("day05_example")
    private val problemInput = resourceAsText("day05")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals("CMZ", Day05(exampleInput, 3).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals("VQZNJMWTR", Day05(problemInput, 9).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals("MCD", Day05(exampleInput, 3).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals("NLCDCLVMQ", Day05(problemInput, 9).puzzle2())
        }
    }
}
