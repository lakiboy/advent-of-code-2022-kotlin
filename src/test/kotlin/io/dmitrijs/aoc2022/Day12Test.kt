package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 12")
internal class Day12Test {
    private val exampleInput = resourceAsLines("day12_example")
    private val problemInput = resourceAsLines("day12")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(31, Day12(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(361, Day12(problemInput).puzzle1())
        }

        @Test
        fun `solves problem with modified dijkstra`() {
            assertEquals(361, Day12(problemInput).puzzle1Dijkstra())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(29, Day12(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(354, Day12(problemInput).puzzle2())
        }
    }
}
