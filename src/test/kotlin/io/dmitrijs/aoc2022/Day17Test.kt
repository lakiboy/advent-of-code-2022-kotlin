package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 17")
internal class Day17Test {
    private val exampleInput = resourceAsText("day17_example")
    private val problemInput = resourceAsText("day17")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals(3_068, Day17(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals(3_106, Day17(problemInput).puzzle1())
        }
    }
}
