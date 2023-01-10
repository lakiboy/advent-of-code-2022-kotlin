package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 9")
internal class Day09Test {
    private val problemInput = resourceAsLines("day09")
    private val exampleInput = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent().lines()

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(13, Day09(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(6_503, Day09(problemInput).puzzle1())
        }
    }

    @Nested
    @DisplayName("Puzzle 2")
    inner class Puzzle2 {
        @Test
        fun `solves example`() {
            assertEquals(1, Day09(exampleInput).puzzle2())
        }

        @Test
        fun `solves example2`() {
            val exampleInput = """
                R 5
                U 8
                L 8
                D 3
                R 17
                D 10
                L 25
                U 20
            """.trimIndent().lines()

            assertEquals(36, Day09(exampleInput).puzzle2())
        }

        @Test
        fun `solves problem`() {
            assertEquals(2_724, Day09(problemInput).puzzle2())
        }
    }
}
