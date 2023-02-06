package io.dmitrijs.aoc2022

import io.dmitrijs.aoc2022.Resources.resourceAsLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("Day 25")
internal class Day25Test {
    private val exampleInput = resourceAsLines("day25_example")
    private val problemInput = resourceAsLines("day25")

    @Nested
    @DisplayName("Puzzle 1")
    inner class Puzzle1 {
        @Test
        fun `solves example`() {
            assertEquals("2=-1=0", Day25(exampleInput).puzzle1())
        }

        @Test
        fun `solves problem`() {
            assertEquals("2-21=02=1-121-2-11-0", Day25(problemInput).puzzle1())
        }
    }
}
