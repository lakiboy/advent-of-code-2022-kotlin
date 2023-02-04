package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 22")
internal class Day22Test {
    private val exampleInput = resourceAsText("day22_example")
    private val problemInput = resourceAsText("day22")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(6_032, Day22(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(117_054, Day22(problemInput).puzzle1())
        }
    }
}
